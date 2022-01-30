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
package de.mixelblocks.coresystem.accounts;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * mixelblocks-coresystem; de.mixelblocks.coresystem.accounts:MinecraftAccountValidator
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 30.01.2022
 */
public class MinecraftAccountValidator {

    private MinecraftAccountValidator() {} // prevent instantiation

    /**
     * Validate a players account by a given name ( Checks api.mojang.com api if exists )
     * @param playerName
     * @return isValid
     */
    public static boolean isValidPlayer(String playerName) {
        try {
            String urlS = "https://api.mojang.com/users/profiles/minecraft/" + playerName;
            URL url = new URL(urlS);
            HttpURLConnection request = (HttpURLConnection)url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
            JsonObject jsonProfile = root.getAsJsonObject();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Resolve the uuid of a player by given username ( returns null if not existing )
     * @param playerName
     * @return uuid
     */
    public static UUID resolveUUID(String playerName) {
        try {
            String uuid = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName).openStream()));
            uuid = (((JsonObject)new JsonParser().parse(in)).get("id")).toString().replaceAll("\"", "");
            uuid = uuid.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
            in.close();
            return UUID.fromString(uuid);
        } catch (Exception e) {
            return null;
        }
    }

}
