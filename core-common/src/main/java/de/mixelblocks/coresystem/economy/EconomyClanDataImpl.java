/*
 * mixelblocks-coresystem | Copyright (C) 2022 | mixelblocks.de | LuciferMorningstarDev
 * Licensed under the MIT License
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * You should have received a copy of the MIT License
 *
 * Repository:
 *     Github:          https://github.com/MixelBlccks/mixelblocks-coresystem
 *
 * Contact:
 *     Discord Server:  https://mixelblocks.de/discord
 *     Website:         https://mixelblocks.de/
 *     DashBoard:       https://dash.mixelblocks.de/
 *     Mail:            contact@mixelblocks.de
 *     Minecraft:       mixelblocks.de:25565
 *
 */
package de.mixelblocks.coresystem.economy;

import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.economy:EconomyClanDataImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class EconomyClanDataImpl implements EconomyClanData {

    private final UUID clanId;
    private Document economyClanDocument;
    private final EconomySystemProvider provider;

    public EconomyClanDataImpl(UUID clanId, EconomySystemProvider provider) {
        this.clanId = clanId;
        this.provider = provider;
        this.economyClanDocument = provider.db().getDocument("clan_accounts", clanId.toString());
        if(this.economyClanDocument == null) {
            this.economyClanDocument = provider.db().buildDocument(clanId.toString(), new Object[][] {
                    {
                            "money", 0D
                    },
                    {
                            "bank", 0D
                    }
            });
            provider.db().insertDocument("user_accounts", this.economyClanDocument);
        }
    }

    @Override
    public boolean addBank(double amount, @NotNull String description) {
        if(amount < 0) return false;
        try {
            double newValue = getBank() + amount;
            economyClanDocument.put("bank", newValue);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        this.addAction(clanId, Transaction.DEPOSIT_MONEY, amount, description);
        return true;
    }

    @Override
    public boolean removeBank(double amount, @NotNull String description) {
        if(amount < 0) return false;
        try {
            double newValue = getBank() - amount;
            economyClanDocument.put("bank", newValue);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        this.addAction(clanId, Transaction.WITHDRAW_MONEY, amount, description);
        return true;
    }

    @Override
    public boolean setBank(double amount, @NotNull String description) {
        try {
            economyClanDocument.put("bank", amount);
            updatePlayerDocument();
        } catch(Exception e) {return false;}
        this.addAction(clanId, Transaction.SET_MONEY, amount, description);
        return true;
    }

    @Override
    public double getBank() {
        return economyClanDocument.getDouble("bank");
    }

    private void addAction(UUID clan, Transaction action, double amount, String description) {
        long time = System.currentTimeMillis();
        provider.db().insertDocument("clan_transactions", provider.db().buildDocument(clan.toString(), new Object[][] {
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
        provider.db().replaceDocument("user_accounts", clanId.toString(), economyClanDocument);
    }

}
