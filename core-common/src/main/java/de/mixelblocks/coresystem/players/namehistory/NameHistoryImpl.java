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
package de.mixelblocks.coresystem.players.namehistory;

import de.mixelblocks.coresystem.CoreSystemProvider;
import de.mixelblocks.coresystem.players.CachedPlayer;
import de.mixelblocks.coresystem.serialization.SerializableSerializer;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players.namehistory:NameHistoryImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class NameHistoryImpl implements NameHistory {

    private final CachedPlayer cachedPlayer;
    private final CoreSystemProvider core;

    private List<NameHistoryEntry> entries;

    private Document nameHistoryDocument;

    public NameHistoryImpl(CachedPlayer cachedPlayer, CoreSystemProvider core) {
        this.cachedPlayer = cachedPlayer;
        this.core = core;
        this.entries = new ArrayList<>();
        this.nameHistoryDocument = core.db().getDocument("nameHistory", cachedPlayer.uuid().toString());
        if(this.nameHistoryDocument == null) {
            this.nameHistoryDocument = core.db().buildDocument(cachedPlayer.uuid().toString(), new Object[][]{
                    {"entries", Arrays.asList(SerializableSerializer.serialize(new NameHistoryEntryImpl(cachedPlayer.username())))}
            });
            core.db().insertDocument("nameHistory", this.nameHistoryDocument);
        }
        List<String> history = this.nameHistoryDocument.getList("entries", String.class);
        for(String entry : history) this.entries.add((NameHistoryEntry) SerializableSerializer.deserialize(entry));
    }

    @Override
    public UUID uuid() {
        return cachedPlayer.uuid();
    }

    @Override
    public String current() {
        return null;
    }

    @Override
    public List<NameHistoryEntry> entries() {
        return entries;
    }

    @Override
    public NameHistoryEntry currentEntry() {
        return null;
    }

    @Override
    public NameHistoryEntry firstJoined() {
        return null;
    }

    @Override
    public void addEntry(String name) {
        List<String> history = new ArrayList<>();
        for(NameHistoryEntry entry : entries) {
            history.add(SerializableSerializer.serialize(entry));
        }
        history.add(SerializableSerializer.serialize(new NameHistoryEntryImpl(name)));
        this.nameHistoryDocument.put("history", history);
        core.db().replaceDocument("nameHistory", cachedPlayer.uuid().toString(), this.nameHistoryDocument);
    }
}
