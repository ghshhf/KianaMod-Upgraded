package kianamod.events;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import kianamod.powers.HonkaiEnergy;

/**
 * 天命总部突入事件
 * 对天命总部的突袭行动。高风险，高回报。
 */
public class TianMingHeadquartersEvent extends AbstractImageEvent {
    public static final String ID = "ExampleMod:TianMingHeadquartersEvent";
    private static final String NAME = "天命总部突入";
    private static final String DESC = "你和同伴们已经潜入天命总部的核心区域。警报已经响起。";
    private static final String[] OPTIONS = {
            "夺取机密文件（失去8点生命，获得1件随机遗物）",
            "破坏能量核心（视情况而定）",
            "撤离（回复10点生命）"
    };

    private int screenNum = 0;

    public TianMingHeadquartersEvent() {
        super(ID, NAME, DESC);
        this.imageEventText.clear();
        this.imageEventText.setDialogOption(OPTIONS[0]);
        this.imageEventText.setDialogOption(OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[2]);
    }

    @Override
    protected void buttonEffect(int buttonPressed) {
        switch (screenNum) {
            case 0:
                this.imageEventText.clearAllDialogs();
                this.imageEventText.setDialogOption("[继续]");

                switch (buttonPressed) {
                    case 0: // 夺取机密文件
                        AbstractDungeon.player.damage(
                                new DamageInfo(null, 8, DamageInfo.DamageType.HP_LOSS));
                        // Get a random relic (any tier)
                        AbstractRelic.RelicTier tier = AbstractDungeon.returnRandomRelicTier();
                        AbstractRelic relic = AbstractDungeon.returnRandomRelic(tier);
                        if (relic != null) {
                            AbstractRelic relicCopy = relic.makeCopy();
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                                    Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f, relicCopy);
                            // Check if it's a Boss relic
                            if (relicCopy.tier == AbstractRelic.RelicTier.BOSS) {
                                AbstractDungeon.actionManager.addToBottom(
                                        new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                                new HonkaiEnergy(AbstractDungeon.player, 3), 3));
                                this.imageEventText.updateBodyText(
                                        "你从机密文件中发现了关于崩坏能的重大情报！获得了额外崩坏能。");
                            } else {
                                this.imageEventText.updateBodyText(
                                        "你成功夺取了一份机密文件，获得了有价值的资源。");
                            }
                        } else {
                            this.imageEventText.updateBodyText("文件柜是空的……白跑了一趟。");
                        }
                        break;

                    case 1: // 破坏能量核心
                        boolean inCombat = AbstractDungeon.getCurrRoom() != null
                                && AbstractDungeon.getCurrRoom().phase
                                   == com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase.COMBAT;
                        if (inCombat) {
                            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                                if (!m.isDeadOrEscaped()) {
                                    int halfHP = m.maxHealth / 2;
                                    if (m.currentHealth > halfHP) {
                                        m.currentHealth = halfHP;
                                        m.healthBarUpdatedEvent();
                                    }
                                }
                            }
                            AbstractDungeon.actionManager.addToBottom(
                                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                            new HonkaiEnergy(AbstractDungeon.player, 1), 1));
                            this.imageEventText.updateBodyText("能量核心的爆炸重创了周围的敌人！");
                        } else {
                            AbstractDungeon.actionManager.addToBottom(
                                    new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                            new HonkaiEnergy(AbstractDungeon.player, 2), 2));
                            AbstractDungeon.player.damage(
                                    new DamageInfo(null, 4, DamageInfo.DamageType.HP_LOSS));
                            this.imageEventText.updateBodyText(
                                    "你破坏了能量核心，崩坏能四溢而出。你受到了一些波及。");
                        }
                        break;

                    case 2: // 撤离
                        AbstractDungeon.player.heal(10);
                        this.imageEventText.updateBodyText("你安全撤离了天命总部，稍作休整。");
                        break;
                }
                screenNum = 1;
                break;

            case 1:
                this.openMap();
                break;
        }
    }
}
