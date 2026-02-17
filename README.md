# MultiLibExample

MultiLibを使用したPaperMCプラグインの実装例です。

## 概要

このプラグインは、MultiLibライブラリの機能を示すデモプラグインです。  
MultiLibは、Spigot/Paperサーバー間でデータを共有するためのライブラリであり、マルチサーバー環境でのプレイヤーやチャンクの情報を管理等を行うことができます。

## 機能

### プレイヤー移動イベント
プレイヤーがワールド内で移動した際に、その位置がローカルチャンクか外部チャンクかを判定し、アクションバーに表示します。

### 永続データ保存
プレイヤーごとにデータを保存・取得するための機能を提供します。

### グローバルストレージ
サーバー間で共有できるグローバルなデータ保存機能を提供します。

### コマンド

| コマンド | 説明 | 権限 |
|---------|------|-----|
| `/mle localcheck` | プレイヤーがローカルかどうかを確認 | mle.localcheck |
| `/mle setdata <key> <value>` | プレイヤーデータを設定 | なし |
| `/mle getdata <key>` | プレイヤーデータを取得 | なし |
| `/mle global set <key> <value>` | グローバルデータを設定 | なし |
| `/mle global get <key>` | グローバルデータを取得 | なし |
| `/mle global list <prefix>` | プレフィックス付きでキーを一覧表示 | なし |

## 必要な環境

- MultiPaper または ShreddedPaper 1.20.1
- Java 17 以上

## インストール

1. このプラグインをコンパイルしてJARファイルを生成
2. 生成されたJARファイルをサーバーのpluginsフォルダに配置
3. サーバーを再起動またはリロード

## ビルド方法

このプロジェクトはGradleを使用してビルドを行います。

```
bash
./gradlew build
```

ビルドが完了すると、`build/libs/` フォルダにJARファイルが生成されます。

## プロジェクト構造

```
src/main/kotlin/dev/kuwa/MultiLibExample/
├── MultiLibExample.kt      # メインプラグインクラス
├── PlayerListener.kt      # プレイヤーイベントリスナー
└── commands/
    └── SiegeCommand.kt    # コマンドハンドラー
```

## 技術スタック

- **言語**: Kotlin
- **ビルドツール**: Gradle (Kotlin DSL)
- **依存ライブラリ**:
  - PaperMC API
  - MultiLib
  - ACF (Aikar Command Framework)