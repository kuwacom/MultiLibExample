package dev.kuwa.MultiLibExample

import co.aikar.commands.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin

import com.github.puregero.multilib.MultiLib
import dev.kuwa.MultiLibExample.commands.MLECommand
import net.kyori.adventure.text.Component

class MultiLibExample : JavaPlugin() {
    lateinit var manager: PaperCommandManager

    final val prefix: String = "==== MultiLibExample ===="

    override fun onEnable() {
        // Plugin startup logic

        logger.info( prefix + "Example Enabled!!")

        // CommandManager を生成
        manager = PaperCommandManager(this)
        // コマンドクラスを登録
        manager.registerCommand(MLECommand())

        // MultiLib
        // https://github.com/MultiPaper/MultiLib

        // MultiLib の通知で String を受け取るチャンネルを登録
        MultiLib.onString(this, "siege:match_start") { data, _ ->
            // data が通知時に送られた文字列
            logger.info("Received match start notification: $data")
            // 例: プレイヤー全員にメッセージを飛ばす
            server.broadcast(Component.text("試合開始: $data"))
        }

        MultiLib.onString(this, "siege:match_end") { data, _ ->
            logger.info("Received match end notification: $data")
            server.broadcast(Component.text("試合終了: $data"))
        }

        // イベント登録（複数サーバー対応）
        server.pluginManager.registerEvents(PlayerListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
