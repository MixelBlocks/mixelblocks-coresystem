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

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.players.options:PlayerOptionsEntryImpl
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class PlayerOptionsEntryImpl implements PlayerOptionsEntry {

    private String value;

    public PlayerOptionsEntryImpl(String value) {
        this.value = value;
    }

    @Override
    public void value(String value) {
        this.value = value;
    }

    @Override
    public String asString() {
        return value;
    }

    @Override
    public boolean asBoolean() {
        return Boolean.valueOf(value);
    }

    @Override
    public int asInt() {
        return Integer.valueOf(value);
    }

    @Override
    public long asLong() {
        return Long.valueOf(value);
    }

    @Override
    public double asDouble() {
        return Double.valueOf(value);
    }

    @Override
    public float asFloat() {
        return Float.valueOf(value);
    }
}
