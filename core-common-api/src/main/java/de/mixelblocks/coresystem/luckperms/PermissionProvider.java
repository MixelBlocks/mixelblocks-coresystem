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

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.permissions:PermissionProvider
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public interface PermissionProvider {

    /**
     * Get the by LuckPerms registered service provider
     * @return
     */
    LuckPerms getLuckPerms();

    /**
     * Get the default group ( higher ranked ) of a player
     * @param uuid
     * @return defaultGroup
     */
    String getDefaultPlayerGroupId(UUID uuid);

    /**
     * Get the default group ( higher ranked ) of a player
     * @param name
     * @return defaultGroup
     */
    String getDefaultPlayerGroupId(String name);

    /**
     * resolve the groups prefix by given group
     * @param groupId
     * @return prefix
     */
    String resolveGroupPrefix(String groupId);

    /**
     * resolve the players higher groups prefix by a given player
     * @param uuid
     * @return prefix
     */
    String resolvePlayerGroupPrefix(UUID uuid);

    String groupFormat(String group);
    String PlayerFormat(UUID uuid);

    /**
     * Check if a group is ranked higher
     * @param group_should_be_higher -> should be higher rank
     * @param group_should_be_lower -> should be the lower rank
     * @return boolean value if they should be higher group is ranked higher
     */
    boolean isHigherGroup(String group_should_be_higher, String group_should_be_lower);

    /**
     * Check if a player is ranked higher
     * @param player_should_be_higher -> should be higher ranked player
     * @param player_should_be_lower -> should be the lower ranked player
     * @return boolean value if they should be higher group is ranked higher
     */
    boolean isHigherPlayer(UUID player_should_be_higher, UUID player_should_be_lower);

    /**
     * resolve cached metadata of a player
     * @param uuid
     * @return data
     */
    CachedMetaData playerMeta(UUID uuid);

    /**
     * resolve cached metadata of a group
     * @param group
     * @return data
     */
    CachedMetaData groupMeta(String group);

    /**
     * get the luckperms user object
     * @param uuid
     * @return user
     */
    User loadUser(UUID uuid);

    /**
     * get the luckperms group object
     * @param group
     * @return group
     */
    Group loadGroup(String group);

    /**
     * resolve the players higher ranked by weight prefix
     * @param uuid
     * @return prefix
     */
    String getPrefix(UUID uuid);

    /**
     * resolve the players higher ranked by weight suffix
     * @param uuid
     * @return suffix
     */
    String getSuffix(UUID uuid);

    /**
     * resolve all active player prefixes comma separated
     * @param uuid
     * @return prefixes
     */
    String getPrefixes(UUID uuid);

    /**
     * resolve all active player suffixes comma separated
     * @param uuid
     * @return suffixes
     */
    String getSuffixes(UUID uuid);

    /**
     * Return the current chat format configuration
     * @return
     */
    ChatFormatConfig config();

}