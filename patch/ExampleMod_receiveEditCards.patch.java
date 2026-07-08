// ============================================================
// Patch snippet for: kianamod.modcore.ExampleMod#receiveEditCards
// ------------------------------------------------------------
// 将下方 12 行 BaseMod.addCard(...) 粘贴进 receiveEditCards() 方法中
// （建议放在现有 BaseMod.addCard(...) 调用之后）。
//
// 需要新增的 import（若 ExampleMod.java 顶部尚未存在）：
//     import kianamod.card.*;
//
// 说明：
//   - 12 张卡的 ID 形如 "ExampleMod:FlamePursuit" 等，与
//     ExampleModResources/localization/ZHS/cards_additions.json 的键完全一致。
//   - BurnOut 卡依赖 kianamod.powers.BurnOutPower（已随本扩展一并提供）。
//   - 强烈建议同时在 receiveEditPowers() 中注册 BurnOutPower，
//     以获得完整的能力提示（tooltip）支持：
//         BaseMod.addPower("ExampleMod", new BurnOutPower("燃尽", "燃尽描述"));
//     （或在 receiveEditPowers 中调用 BaseMod.addPower(...) 注册该能力）
// ============================================================

// --- begin: 粘贴到 receiveEditCards() ---
BaseMod.addCard(new FlamePursuit());
BaseMod.addCard(new CrimsonCharge());
BaseMod.addCard(new PassFlame());
BaseMod.addCard(new SkyfireDraw());
BaseMod.addCard(new EmberRekindle());
BaseMod.addCard(new BurnOut());
BaseMod.addCard(new ValkyrieOath());
BaseMod.addCard(new FlameStorm());
BaseMod.addCard(new Cinder());
BaseMod.addCard(new PrairieFire());
BaseMod.addCard(new FlameDragonBreak());
BaseMod.addCard(new GuardianFlame());
// --- end ---
