package de.mixelblocks.coresystem.players.meta;

import de.mixelblocks.coresystem.CoreSystemProvider;
import de.mixelblocks.coresystem.players.CachedPlayer;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players.meta:PlayerMetaImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class PlayerMetaImpl implements PlayerMeta {

    private final CachedPlayer cachedPlayer;
    private final CoreSystemProvider core;

    public PlayerMetaImpl(CachedPlayer cachedPlayer, CoreSystemProvider core) {
        this.cachedPlayer = cachedPlayer;
        this.core = core;
    }

    @Override
    public void update() {
        core.perms().loadUser(cachedPlayer.uuid());
    }

    @Override
    public String prefix() {
        return core.perms().getPrefix(cachedPlayer.uuid());
    }

    @Override
    public String suffix() {
        return core.perms().getSuffix(cachedPlayer.uuid());
    }

    @Override
    public String prefixes() {
        return core.perms().getPrefixes(cachedPlayer.uuid());
    }

    @Override
    public String suffixes() {
        return core.perms().getSuffixes(cachedPlayer.uuid());
    }
}
