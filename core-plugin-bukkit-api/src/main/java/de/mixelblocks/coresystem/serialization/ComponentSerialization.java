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
package de.mixelblocks.coresystem.serialization;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.serialization:ComponentSerialization
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class ComponentSerialization {

    private ComponentSerialization() {} // prevent instantiation

    public static final LegacyComponentSerializer
            sectionAndHEX = LegacyComponentSerializer.builder()
            .character('§').hexCharacter('#').hexColors().build(),
            unusualSectionAndHEX = LegacyComponentSerializer.builder()
                    .character('§').hexCharacter('#').hexColors()
                    .useUnusualXRepeatedCharacterHexFormat().build(),
            etAndHEX = LegacyComponentSerializer.builder()
                    .character('&').hexCharacter('#').hexColors().build(),
            etOnly = LegacyComponentSerializer.builder()
                    .character('&').build(),
            sectionOnly = LegacyComponentSerializer.builder()
                    .character('§').build();

}
