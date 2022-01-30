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

import java.io.File;
import java.io.IOException;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.configuration:JsonConfig
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class JsonConfig<T> implements Config {

    private final File dataFile;

    private Class<T> configurationClazz;

    private T configuration;

    /**
     * JsonConfig which can hold plain Java Objects which are GSON serializable ( needs to have Full Args Constructor AND Getter and Setter for each field )
     * @param clazz GSON serializable class
     * @param dataFile the configuration file
     */
    public JsonConfig(Class<T> clazz, File dataFile) {
        this.dataFile = dataFile;
        this.configurationClazz = clazz;
    }

    @Override
    public void setDefault(Class clazz, Object defaultConfig) {
        this.configurationClazz = clazz;
        this.configuration = (T) defaultConfig;
    }

    @Override
    public void load() throws IOException {
        if(dataFile.exists()) configuration = ConfigLoader.loadConfig(configurationClazz, dataFile);
    }

    @Override
    public void load(boolean overrideDefault) throws IOException {
        if(!overrideDefault && configuration != null) return;
        load();
    }

    @Override
    public void save() throws IOException {
        ConfigLoader.saveConfig(configuration, dataFile);
    }

    @Override
    public void save(boolean overwrite) throws IOException {
        if(!overwrite && dataFile.exists()) return;
        save();
    }

    @Override
    public T get() {
        return configuration;
    }

}
