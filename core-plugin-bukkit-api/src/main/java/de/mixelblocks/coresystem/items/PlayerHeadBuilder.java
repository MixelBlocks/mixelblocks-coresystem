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
package de.mixelblocks.coresystem.items;

import de.mixelblocks.coresystem.accounts.MinecraftAccountValidator;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.items:PlayerHeadBuilder
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class PlayerHeadBuilder {

    private ItemBuilder itemBuilder;
    private SkullMeta skullMeta;

    private UUID ownerUuid;

    public PlayerHeadBuilder() {
        this.itemBuilder = new ItemBuilder(Material.PLAYER_HEAD);
    }

    public PlayerHeadBuilder owner(String owner) throws IllegalArgumentException {
        this.ownerUuid = MinecraftAccountValidator.resolveUUID(owner);
        if(this.ownerUuid == null) throw new IllegalArgumentException("This player does not exist at the mojang database!");
        return this;
    }

    public PlayerHeadBuilder owner(UUID owner) {
        this.ownerUuid = owner;
        return this;
    }

    public PlayerHeadBuilder name(String name) {
        itemBuilder.name(name);
        return this;
    }

    public PlayerHeadBuilder displayName(String name) {
        itemBuilder.displayName(name);
        return this;
    }

    public ItemStack build() {
        ItemStack skull = this.itemBuilder.build();
        skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(ownerUuid));
        skull.setItemMeta(skullMeta);
        return skull;
    }

    public ItemBuilder getItemBuilder() {
        return itemBuilder;
    }
}
