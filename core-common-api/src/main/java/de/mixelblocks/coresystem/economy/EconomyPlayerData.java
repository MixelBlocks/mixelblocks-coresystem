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

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.economy:EconomyPlayerData
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface EconomyPlayerData {

    /**
     * Add money (cash) to player economy data
     * @param amount
     * @return success
     */
    boolean addMoney(double amount);

    /**
     * Remove money (cash) from player economy data
     * @param amount
     * @return success
     */
    boolean removeMoney(double amount);

    /**
     * Set money (cash) of player economy data
     * @param amount
     * @return success
     */
    boolean setMoney(double amount);

    /**
     * resolve the available amount of players (cash)
     * @return amount
     */
    double getMoney();

    /**
     * Add money (bank account) to player economy data
     * @param amount
     * @param description ( The reason in dashboard displayed as reason for transaction )
     * @return success
     */
    boolean addBank(double amount, String description);

    /**
     * Remove money (bank account) to player economy data
     * @param amount
     * @param description ( The reason in dashboard displayed as reason for transaction )
     * @return success
     */
    boolean removeBank(double amount, String description);

    /**
     * Set money (bank account) of player economy data
     * @param amount
     * @param description ( The reason in dashboard displayed as reason for set things )
     * @return success
     */
    boolean setBank(double amount, String description);

    /**
     * resolve the available amount of players (bank account)
     * @return amount
     */
    double getBank();

    /**
     * Add money to ManagedType ( bank or cash )
     * @param type
     * @param amount
     * @param reason
     * @return success
     */
    default boolean add(ManagedType type, double amount, String reason) {
        boolean success = false;
        switch(type) {
            case BANK:
                try {
                    return this.addBank(amount, reason);
                } catch(Exception e) { success = false; break;}
            case CASH:
                try {
                    return this.addMoney(amount);
                } catch(Exception e) { success = false; break;}
        }
        return success;
    }

    /**
     * Remove money from ManagedType ( bank or cash )
     * @param type
     * @param amount
     * @param reason
     * @return success
     */
    default boolean rm(ManagedType type, double amount, String reason) {
        switch(type) {
            case BANK:
                try {
                    return this.removeBank(amount, reason);
                } catch(Exception e) { break;}
            case CASH:
                try {
                    return this.removeMoney(amount);
                } catch(Exception e) { break;}
        }
        return false;
    }

    /**
     * Set money of ManagedType ( bank or cash )
     * @param type
     * @param amount
     * @param reason
     * @return success
     */
    default boolean set(ManagedType type, double amount, String reason) {
        switch(type) {
            case BANK:
                try {
                    return this.setBank(amount, reason);
                } catch(Exception e) { break;}
            case CASH:
                try {
                    return this.setMoney(amount);
                } catch(Exception e) { break;}
        }
        return false;
    }

    /**
     * The type if cash or bank
     */
    enum ManagedType {
        /**
         * Use BANK for the bank account as type
         */
        BANK,
        /**
         * Use CASH for the players cash thing as type
         */
        CASH;
    }

}