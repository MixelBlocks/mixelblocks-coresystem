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

import de.mixelblocks.coresystem.CoreConstants;
import de.mixelblocks.coresystem.mongodb.MongoDatabaseHandler;

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.economy:EconomySystemProvider
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface EconomySystemProvider {

    /**
     * The long name of the currency like MixelCoins
     * @return
     */
    default String currencyName() {
        return CoreConstants.CURRENCY_NAME;
    }

    /**
     * Short name of currency like MC ( MixelCoins in this case )
     * @return
     */
    default String currency() {
        return CoreConstants.CURRENCY_SHORT;
    }

    /**
     * resolve the economy data of a player by given uuid
     * @param uuid
     * @return economyData
     */
    EconomyPlayerData playerData(UUID uuid);

    /**
     * Get the database handler for economy system
     * @return databaseHandler
     */
    MongoDatabaseHandler db();

}