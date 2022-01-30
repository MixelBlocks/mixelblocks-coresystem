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
package de.mixelblocks.coresystem.players;

import de.mixelblocks.coresystem.accounts.MinecraftAccountValidator;
import de.mixelblocks.coresystem.economy.EconomyPlayerData;
import de.mixelblocks.coresystem.players.meta.PlayerMeta;
import de.mixelblocks.coresystem.players.namehistory.NameHistory;
import de.mixelblocks.coresystem.players.options.PlayerOptions;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players:CachedPlayer
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface CachedPlayer {

    UUID uuid();
    String username();
    String displayName();

    boolean online();

    NameHistory nameHistory();
    PlayerOptions options();
    PlayerMeta meta();
    EconomyPlayerData economyData();

    default void addMoney(final double amount) {
        economyData().addMoney(amount);
    }
    default void removeMoney(final double amount) {
        economyData().removeMoney(amount);
    }
    default void setMoney(final double amount) {
        economyData().setMoney(amount);
    }
    default double getMoney() {
        return economyData().getMoney();
    }

    default void addBank(final double amount, String reason) {
        economyData().addBank(amount, reason);
    }
    default void removeBank(final double amount, String reason) {
        economyData().removeBank(amount, reason);
    }
    default void setBank(final double amount, String reason) {
        economyData().setBank(amount, reason);
    }
    default double getBank() {
        return economyData().getBank();
    }

    default String getMoneyFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(getMoney());
    }
    default String getBankFormatted() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(getBank());
    }

    static boolean valid(String name) {
        return MinecraftAccountValidator.isValidPlayer(name);
    }

    static UUID resolveUUID(String name) {
        return MinecraftAccountValidator.resolveUUID(name);
    }

}