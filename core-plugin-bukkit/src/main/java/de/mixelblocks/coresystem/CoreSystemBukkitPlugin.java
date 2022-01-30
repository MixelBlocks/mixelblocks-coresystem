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

import de.mixelblocks.coresystem.configuration.JsonConfig;
import de.mixelblocks.coresystem.economy.EconomySystemProvider;
import de.mixelblocks.coresystem.economy.EconomySystemProviderImpl;
import de.mixelblocks.coresystem.locationa.CachedLocationProviderimpl;
import de.mixelblocks.coresystem.locations.CachedLocationProvider;
import de.mixelblocks.coresystem.luckperms.ChatFormatConfig;
import de.mixelblocks.coresystem.luckperms.DefaultChatFormatConfig;
import de.mixelblocks.coresystem.luckperms.PermissionProvider;
import de.mixelblocks.coresystem.luckperms.PermissionProviderImpl;
import de.mixelblocks.coresystem.mongodb.DefaultMongoDatabaseConfig;
import de.mixelblocks.coresystem.mongodb.MongoDatabaseConfig;
import de.mixelblocks.coresystem.mongodb.MongoDatabaseHandler;
import de.mixelblocks.coresystem.players.CorePlayerProvider;
import de.mixelblocks.coresystem.players.CorePlayerProviderImpl;
import de.mixelblocks.coresystem.redis.RedisConfig;
import de.mixelblocks.coresystem.redis.RedisProvider;
import de.mixelblocks.coresystem.redis.RedisProviderImpl;
import de.mixelblocks.coresystem.redis.messaging.MessagingProvider;
import de.mixelblocks.coresystem.teleport.homes.HomeSystemProvider;
import de.mixelblocks.coresystem.teleport.homes.HomeSystemProviderImpl;
import de.mixelblocks.coresystem.teleport.warps.WarpSystemProvider;
import de.mixelblocks.coresystem.teleport.warps.WarpSystemProviderImpl;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem:CoreSystemBukkitPlugin
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class CoreSystemBukkitPlugin extends JavaPlugin {

    private JsonConfig<RedisConfig> redisConfig;
    private JsonConfig<MongoDatabaseConfig> databaseConfig;
    private JsonConfig<ChatFormatConfig> chatFormatConfig;

    private LuckPerms luckPerms;

    private CachedLocationProvider cachedLocationProvider;
    private EconomySystemProvider economySystemProvider;
    private CoreSystemProvider coreSystemProvider;
    private CorePlayerProvider corePlayerProvider;
    private PermissionProvider permissionProvider;
    private HomeSystemProvider homeSystemProvider;
    private WarpSystemProvider warpSystemProvider;
    private MessagingProvider messagingProvider;
    private RedisProvider redisProvider;

    private BukkitCore bukkitCore;

    private MongoDatabaseHandler mongoDatabaseHandler;
    private MongoDatabaseHandler economyMongoDatabaseHandler;

    @Override
    public void onEnable() {
        this.initConfigurations();
        this.initDatabases();
        this.registerServiceProvider();
        this.registerCommandsAndListeners();
        this.getLogger().info("CoreSystem enabled...");
    }

    @Override
    public void onDisable() {
        Bukkit.getServicesManager().unregisterAll(this);
        this.getLogger().info("CoreSystem enabled...");
    }

    private void initConfigurations() {
        this.redisConfig = new JsonConfig<>(RedisConfig.class, new File(this.getDataFolder(), CoreConstants.CONFIG_PATH_NAME + "redis.json"));
        this.redisConfig.setDefault(RedisConfig.class, new RedisConfig("redis://127.0.0.1:6379/", "default", "password"));
        try {
            this.redisConfig.load(true);
            this.redisConfig.save(false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        this.databaseConfig = new JsonConfig<>(MongoDatabaseConfig.class, new File(this.getDataFolder(), CoreConstants.CONFIG_PATH_NAME + "database.json"));
        this.databaseConfig.setDefault(MongoDatabaseConfig.class, new DefaultMongoDatabaseConfig());
        try {
            this.databaseConfig.load(true);
            this.databaseConfig.save(false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        this.chatFormatConfig = new JsonConfig<>(ChatFormatConfig.class, new File(this.getDataFolder(), CoreConstants.CONFIG_PATH_NAME + "chatformat.json"));
        this.chatFormatConfig.setDefault(ChatFormatConfig.class, new DefaultChatFormatConfig());
        try {
            this.chatFormatConfig.load(true);
            this.chatFormatConfig.save(false);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void initDatabases() {
        this.mongoDatabaseHandler = MongoDatabaseHandler.fromMongoDatabaseConfig(databaseConfig.get());
        this.economyMongoDatabaseHandler = MongoDatabaseHandler.fromMongoDatabaseConfig(new MongoDatabaseConfig(databaseConfig.get().getConnectionString(), "economy"));
    }

    private void registerServiceProvider() {
        RegisteredServiceProvider<LuckPerms> luckpermsServiceProvider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if(luckpermsServiceProvider != null) this.luckPerms = luckpermsServiceProvider.getProvider();

        this.permissionProvider = new PermissionProviderImpl(chatFormatConfig.get(), this.luckPerms);
        this.redisProvider = new RedisProviderImpl(this.redisConfig.get());
        this.messagingProvider = this.redisProvider.messaging();
        this.economySystemProvider = new EconomySystemProviderImpl(this.economyMongoDatabaseHandler);
        this.corePlayerProvider = new CorePlayerProviderImpl(this);
        this.cachedLocationProvider = new CachedLocationProviderimpl(this.redisProvider.client());
        this.warpSystemProvider = new WarpSystemProviderImpl(this.cachedLocationProvider);
        this.homeSystemProvider = new HomeSystemProviderImpl(this.mongoDatabaseHandler);
        this.bukkitCore = new BukkitCoreimpl(this.coreSystemProvider, this);
        this.coreSystemProvider = new CoreSystemProviderImpl(
                this.mongoDatabaseHandler,
                this.redisProvider,
                this.economySystemProvider,
                this.corePlayerProvider,
                this.permissionProvider,
                this.cachedLocationProvider,
                this.homeSystemProvider,
                this.warpSystemProvider
        );
        CoreSystemAPI.setProvider(this.coreSystemProvider);
        BukkitCoreAPI.setProviders(this.bukkitCore, this.coreSystemProvider);
        Bukkit.getServicesManager().register(CoreSystemProvider.class, this.coreSystemProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(BukkitCore.class, this.bukkitCore, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(CachedLocationProvider.class, this.cachedLocationProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(EconomySystemProvider.class, this.economySystemProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(CorePlayerProvider.class, this.corePlayerProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(PermissionProvider.class, this.permissionProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(HomeSystemProvider.class, this.homeSystemProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(WarpSystemProvider.class, this.warpSystemProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(RedisProvider.class, this.redisProvider, this, ServicePriority.Normal);
        Bukkit.getServicesManager().register(MessagingProvider.class, this.messagingProvider, this, ServicePriority.Normal);
    }

    private void registerCommandsAndListeners() {

    }

    public MongoDatabaseHandler getMongoDatabaseHandler() {
        return mongoDatabaseHandler;
    }

    public EconomySystemProvider getEconomySystemProvider() {
        return economySystemProvider;
    }

    public JsonConfig<RedisConfig> getRedisConfig() {
        return redisConfig;
    }

    public CorePlayerProvider getCorePlayerProvider() {
        return corePlayerProvider;
    }

    public JsonConfig<ChatFormatConfig> getChatFormatConfig() {
        return chatFormatConfig;
    }

    public JsonConfig<MongoDatabaseConfig> getDatabaseConfig() {
        return databaseConfig;
    }

    public CachedLocationProvider getCachedLocationProvider() {
        return cachedLocationProvider;
    }

    public CoreSystemProvider getCoreSystemProvider() {
        return coreSystemProvider;
    }

    public BukkitCore getBukkitCore() {
        return bukkitCore;
    }

    public HomeSystemProvider getHomeSystemProvider() {
        return homeSystemProvider;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public MessagingProvider getMessagingProvider() {
        return messagingProvider;
    }

    public MongoDatabaseHandler getEconomyMongoDatabaseHandler() {
        return economyMongoDatabaseHandler;
    }

    public PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }

    public RedisProvider getRedisProvider() {
        return redisProvider;
    }

    public WarpSystemProvider getWarpSystemProvider() {
        return warpSystemProvider;
    }

}
