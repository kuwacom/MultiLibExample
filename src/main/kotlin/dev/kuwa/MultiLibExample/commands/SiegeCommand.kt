package dev.kuwa.MultiLibExample.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.*
import org.bukkit.entity.Player
import com.github.puregero.multilib.MultiLib

@CommandAlias("mle")
class MLECommand : BaseCommand() {

    @Subcommand("localcheck")
    @CommandPermission("mle.localcheck")
    fun onLocalCheck(player: Player) {
        val local = MultiLib.isLocalPlayer(player)
        player.sendMessage("Local?: $local")
    }

    @Subcommand("setdata")
//    @Syntax("<key> <value>")
    fun onSetData(
        player: Player,
        @Name("key") key: String,
        @Name("value") value: String
    ) {
        MultiLib.setPersistentData(player, key, value)
        player.sendMessage("Set: $key=$value")
    }

    @Subcommand("getdata")
    fun onGetData(player: Player, key: String) {
        val value = MultiLib.getPersistentData(player, key) ?: "null"
        player.sendMessage("$key=$value")
    }

    @Subcommand("global set")
    fun onGlobalSet(player: Player, key: String, value: String) {
        // Async にセットします
        MultiLib.getDataStorage().set(key, value).thenAccept { result ->
            player.sendMessage("GlobalStorage set: $key = $value")
        }
    }

    @Subcommand("global get")
    fun onGlobalGet(player: Player, key: String) {
        // Async に取得して処理
        MultiLib.getDataStorage().get(key).thenAccept { value ->
            val msg = value ?: "null"
            player.sendMessage("GlobalStorage get: $key = $msg")
        }
    }

    @Subcommand("global list")
    @CommandCompletion("@nothing")  // タブ補完（プレフィックス無しでもOK）
    fun onGlobalList(player: Player, prefix: String) {
        MultiLib.getDataStorage().list(prefix).thenAccept { map ->
            if (map.isEmpty()) {
                player.sendMessage("No keys found with prefix: $prefix")
            } else {
                player.sendMessage("Listing keys with prefix $prefix:")
                for ((k, v) in map) {
                    player.sendMessage(" - $k = $v")
                }
            }
        }
    }
    @Subcommand("global")
    fun onGlobalHelp(player: Player) {
        player.sendMessage("Use /mle global < set | get | list >")
    }

    @Default
    fun help(player: Player) {
        player.sendMessage("Use /mle < localcheck | getdata | setdata | global set, get, list >")
    }
}
