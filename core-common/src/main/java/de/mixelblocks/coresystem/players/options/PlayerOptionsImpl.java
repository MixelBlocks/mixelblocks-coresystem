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
package de.mixelblocks.coresystem.players.options;

import de.mixelblocks.coresystem.CoreSystemProvider;
import de.mixelblocks.coresystem.players.CachedPlayer;
import de.mixelblocks.coresystem.players.namehistory.NameHistoryEntryImpl;
import de.mixelblocks.coresystem.serialization.SerializableSerializer;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players.options:PlayerOptionsImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class PlayerOptionsImpl implements PlayerOptions {

    private final CachedPlayer cachedPlayer;
    private final CoreSystemProvider core;

    private Document optionsDocument;

    private Map<String, PlayerOptionsEntry> entries;

    public PlayerOptionsImpl(CachedPlayer cachedPlayer, CoreSystemProvider core) {
        this.cachedPlayer = cachedPlayer;
        this.core = core;
        this.entries = new HashMap<>();
        this.optionsDocument = core.db().getDocument("nameHistory", cachedPlayer.uuid().toString());
        if(this.optionsDocument == null) {
            this.optionsDocument = core.db().buildDocument(cachedPlayer.uuid().toString(), new Object[][]{});
            core.db().insertDocument("playerOptions", this.optionsDocument);
        }
        this.optionsDocument.keySet().forEach(key -> {
            if(key != "documentName" && key != "_id") {
                entries.put(key, new PlayerOptionsEntryImpl(this.optionsDocument.getString(key)));
            }
        });
    }

    private void update() {
        for(String key : entries.keySet()) {
            this.optionsDocument.put(key, this.entries.get(key).asString());
        }
        core.db().replaceDocument("playerOptions", cachedPlayer.uuid().toString(), this.optionsDocument);
    }

    @Override
    public void set(String key, String value) {
        this.entries.put(key, new PlayerOptionsEntryImpl(value));
        update();
    }

    @Override
    public void set(String key, boolean value) {
        this.entries.put(key, new PlayerOptionsEntryImpl(Boolean.toString(value)));
        update();
    }

    @Override
    public void set(String key, int value) {
        this.entries.put(key, new PlayerOptionsEntryImpl(Integer.toString(value)));
        update();
    }

    @Override
    public void set(String key, long value) {
        this.entries.put(key, new PlayerOptionsEntryImpl(Long.toString(value)));
        update();
    }

    @Override
    public void set(String key, double value) {
        this.entries.put(key, new PlayerOptionsEntryImpl(Double.toString(value)));
        update();
    }

    @Override
    public void set(String key, float value) {
        this.entries.put(key, new PlayerOptionsEntryImpl(Float.toString(value)));
        update();
    }

    @Override
    public void remove(String key) {
        this.entries.remove(key);
        update();
    }

    @Override
    public boolean existEntry(String key) {
        return this.entries.containsKey(key);
    }

    @Override
    public PlayerOptionsEntry getEntry(String key) {
        return this.entries.get(key);
    }
}
