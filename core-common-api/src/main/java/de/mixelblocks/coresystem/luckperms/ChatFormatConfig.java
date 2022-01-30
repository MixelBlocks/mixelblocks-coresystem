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
package de.mixelblocks.coresystem.luckperms;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.luckperms:ChatFormatConfig
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class ChatFormatConfig implements Serializable {


    private String defaultChatFormat;
    private Map<String, String> groupFormats;
    private Map<String, String> prefixOverwrites;

    public ChatFormatConfig(String defaultChatFormat, Map<String, String> groupFormats, Map<String, String> prefixOverwrites) {
        if(groupFormats == null) groupFormats = new HashMap<>();
        if(prefixOverwrites == null) prefixOverwrites = new HashMap<>();
        this.groupFormats = groupFormats;
        this.prefixOverwrites = prefixOverwrites;
        this.defaultChatFormat = defaultChatFormat;
    }

    public String getDefaultChatFormat() {
        return defaultChatFormat;
    }

    public void setDefaultChatFormat(String defaultChatFormat) {
        if(defaultChatFormat == null || defaultChatFormat == "") defaultChatFormat = "<{username}> {message}";
        this.defaultChatFormat = defaultChatFormat;
    }

    public Map<String, String> getGroupFormats() {
        return groupFormats;
    }

    public void setGroupFormats(Map<String, String> groupFormats) {
        if(groupFormats == null) groupFormats = new HashMap<>();
        this.groupFormats = groupFormats;
    }

    public Map<String, String> getPrefixOverwrites() {
        return prefixOverwrites;
    }

    public void setPrefixOverwrites(Map<String, String> prefixOverwrites) {
        if(prefixOverwrites == null) prefixOverwrites = new HashMap<>();
        this.prefixOverwrites = prefixOverwrites;
    }

}
