package de.mixelblocks.coresystem.economy;

import de.mixelblocks.coresystem.players.CachedPlayer;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.economy:EconomyPlayerDataImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class EconomyPlayerDataImpl implements EconomyPlayerData {

    private final UUID uuid;

    private Document economyPlayerDocument;

    private final EconomySystemProvider provider;

    public EconomyPlayerDataImpl(CachedPlayer player, EconomySystemProvider provider) {
        this.uuid = player.uuid();
        this.provider = provider;
        this.economyPlayerDocument = provider.db().getDocument("user_accounts", player.uuid().toString());
        if(this.economyPlayerDocument == null) {
            this.economyPlayerDocument = provider.db().buildDocument(player.uuid().toString(), new Object[][] {
                    {
                            "money", 0D
                    },
                    {
                            "bank", 0D
                    }
            });
            provider.db().insertDocument("user_accounts", this.economyPlayerDocument);
        }
    }

    public EconomyPlayerDataImpl(UUID uuid, EconomySystemProvider provider) {
        this.uuid = uuid;
        this.provider = provider;
        this.economyPlayerDocument = provider.db().getDocument("user_accounts", uuid.toString());
        if(this.economyPlayerDocument == null) {
            this.economyPlayerDocument = provider.db().buildDocument(uuid.toString(), new Object[][] {
                    {
                            "money", 0D
                    },
                    {
                            "bank", 0D
                    }
            });
            provider.db().insertDocument("user_accounts", this.economyPlayerDocument);
        }
    }

    @Override
    public boolean addMoney(double amount) {
        if(amount < 0) return false;
        try {
            double newValue = getBank() + amount;
            economyPlayerDocument.put("money", newValue);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        return true;
    }

    @Override
    public boolean removeMoney(double amount) {
        if(amount < 0) return false;
        try {
            double newValue = getBank() - amount;
            economyPlayerDocument.put("money", newValue);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        return true;
    }

    @Override
    public boolean setMoney(double amount) {
        try {
            economyPlayerDocument.put("money", amount);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        return true;
    }

    @Override
    public double getMoney() {
        return economyPlayerDocument.getDouble("money");
    }

    @Override
    public boolean addBank(double amount, @NotNull String description) {
        if(amount < 0) return false;
        try {
            double newValue = getBank() + amount;
            economyPlayerDocument.put("bank", newValue);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        this.addAction(uuid, Transaction.DEPOSIT_MONEY, amount, description);
        return true;
    }

    @Override
    public boolean removeBank(double amount, @NotNull String description) {
        if(amount < 0) return false;
        try {
            double newValue = getBank() - amount;
            economyPlayerDocument.put("bank", newValue);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        this.addAction(uuid, Transaction.WITHDRAW_MONEY, amount, description);
        return true;
    }

    @Override
    public boolean setBank(double amount, @NotNull String description) {
        try {
            economyPlayerDocument.put("bank", amount);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        this.addAction(uuid, Transaction.SET_MONEY, amount, description);
        return true;
    }

    @Override
    public double getBank() {
        return economyPlayerDocument.getDouble("bank");
    }

    private void addAction(UUID player, Transaction action, double amount, String description) {
        long time = System.currentTimeMillis();
        provider.db().insertDocument("user_transactions", provider.db().buildDocument(player.toString(), new Object[][] {
                {
                        "action", action.getName()
                },
                {
                        "amount", amount
                },
                {
                        "description", description
                },
                {
                        "createdAt", time
                }
        }));
    }

    private void updatePlayerDocument() {
        provider.db().replaceDocument("user_accounts", uuid.toString(), economyPlayerDocument);
    }

}
