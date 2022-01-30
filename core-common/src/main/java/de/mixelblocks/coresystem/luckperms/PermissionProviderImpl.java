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

import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;

import java.util.SortedMap;
import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.luckperms:PermissionProviderImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class PermissionProviderImpl implements PermissionProvider {

    private final LuckPerms luckPerms;
    private ChatFormatConfig chatFormatConfig;

    public PermissionProviderImpl(ChatFormatConfig chatFormatConfig, LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
        this.chatFormatConfig = chatFormatConfig;
    }

    @Override
    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    @Override
    public String getDefaultPlayerGroupId(UUID uuid) {
        try {
            return luckPerms.getUserManager().getUser(uuid).getPrimaryGroup();
        } catch(Exception except) {
            return null;
        }
    }

    @Override
    public String getDefaultPlayerGroupId(String name) {
        try {
            return luckPerms.getUserManager().getUser(name).getPrimaryGroup();
        } catch(Exception except) {
            return null;
        }
    }

    @Override
    public String resolveGroupPrefix(String groupId) {
        return loadGroup(groupId).getCachedData().getMetaData().getPrefix();
    }

    @Override
    public String resolvePlayerGroupPrefix(UUID uuid) {
        return resolveGroupPrefix(getDefaultPlayerGroupId(uuid));
    }

    @Override
    public String groupFormat(String group) {
        ChatFormatConfig conf = chatFormatConfig;
        String format  = conf.getGroupFormats().get(group);
        return (format != null ? format.split("::")[1] : conf.getDefaultChatFormat());
    }

    @Override
    public String PlayerFormat(UUID uuid) {
        return groupFormat(getDefaultPlayerGroupId(uuid));
    }

    @Override
    public boolean isHigherGroup(String group_should_be_higher, String group_should_be_lower) {
        int higher = luckPerms.getGroupManager().getGroup(group_should_be_higher).getWeight() != null
                ? luckPerms.getGroupManager().getGroup(group_should_be_higher).getWeight().getAsInt() : 0;
        int lower = luckPerms.getGroupManager().getGroup(group_should_be_lower).getWeight() != null
                ? luckPerms.getGroupManager().getGroup(group_should_be_lower).getWeight().getAsInt() : 0;
        if(higher <= lower) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isHigherPlayer(UUID player_should_be_higher, UUID player_should_be_lower) {
        return isHigherGroup(resolvePlayerGroupPrefix(player_should_be_higher), resolvePlayerGroupPrefix(player_should_be_lower));
    }

    @Override
    public CachedMetaData playerMeta(UUID uuid) {
        return loadUser(uuid).getCachedData().getMetaData();
    }

    @Override
    public CachedMetaData groupMeta(String group) {
        return getLuckPerms().getGroupManager().getGroup(group).getCachedData().getMetaData();
    }

    @Override
    public User loadUser(UUID uuid) {
        return getLuckPerms().getUserManager().getUser(uuid);
    }

    @Override
    public Group loadGroup(String group) {
        return getLuckPerms().getGroupManager().getGroup(group);
    }

    @Override
    public String getPrefix(UUID uuid) {
        String prefix = playerMeta(uuid).getPrefix();
        return (prefix != null) ? prefix : "";
    }

    @Override
    public String getSuffix(UUID uuid) {
        String suffix = playerMeta(uuid).getSuffix();
        return (suffix != null) ? suffix : "";
    }

    @Override
    public String getPrefixes(UUID uuid) {
        SortedMap<Integer, String> map = playerMeta(uuid).getPrefixes();
        StringBuilder prefixes = new StringBuilder();
        for (String prefix : map.values())
            prefixes.append(prefix);
        return prefixes.toString();
    }

    @Override
    public String getSuffixes(UUID uuid) {
        SortedMap<Integer, String> map = playerMeta(uuid).getSuffixes();
        StringBuilder suffixes = new StringBuilder();
        for (String prefix : map.values())
            suffixes.append(prefix);
        return suffixes.toString();
    }

    @Override
    public ChatFormatConfig config() {
        return chatFormatConfig;
    }

}
