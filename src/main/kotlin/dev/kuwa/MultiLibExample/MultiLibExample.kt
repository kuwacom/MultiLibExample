package dev.kuwa.MultiLibExample

import co.aikar.commands.PaperCommandManager
import org.bukkit.plugin.java.JavaPlugin

import com.github.puregero.multilib.MultiLib
import dev.kuwa.MultiLibExample.commands.MLECommand

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

        // グローバルデータ保存のコールバック登録
        MultiLib.onString(this, "example:chatdata") { data, reply ->
            logger.info(prefix + "Received cross-server data: $data")
            reply.accept("ok", "メッセージ受信")
        }

        // イベント登録（複数サーバー対応）
        server.pluginManager.registerEvents(PlayerListener(), this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
