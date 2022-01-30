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
package de.mixelblocks.coresystem.configuration;

import java.io.IOException;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.configuration:Config
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface Config {

    /**
     * Set the default instance of the config
     * @param clazz GSON serializable class
     * @param defaultConfig
     */
    void setDefault(Class clazz, Object defaultConfig);

    /**
     * Load Configuration
     * @throws IOException
     */
    void load() throws IOException;

    /**
     * Load Configuration
     * @param overrideDefault
     * @throws IOException
     */
    void load(boolean overrideDefault) throws IOException;

    /**
     * Save configuration do tile
     * @throws IOException
     */
    void save() throws IOException;

    /**
     * Save configuration do tile
     * @param overwrite set to true if config should be overwritten
     * @throws IOException
     */
    void save(boolean overwrite) throws IOException;

    /**
     * Get the loaded config object
     * @return configuration
     */
    Object get();

}