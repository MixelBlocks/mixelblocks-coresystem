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
package de.mixelblocks.coresystem;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem:BukkitCoreAPI
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class BukkitCoreAPI {

    private static CoreSystemProvider provider;
    private static BukkitCore bukkitProvider;

    /**
     * This is the static access to the mixel core system povider
     * @return provider
     */
    public static CoreSystemProvider getProvider() {
        return provider;
    }

    /**
     * This is the static access to the mixel bukkit core provider
     * @return bukkitProvider
     */
    public static BukkitCore getBukkitProvider() {
        return bukkitProvider;
    }

    /**
     * Package private method to set the providers
     * @param bukkitCore
     * @param provider
     */
    static void setProviders(BukkitCore bukkitCore, CoreSystemProvider provider) {
        BukkitCoreAPI.provider = provider;
        BukkitCoreAPI.bukkitProvider = bukkitProvider;
    }

}
