package kianamod.events;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import kianamod.powers.EternalBlazingFlamePower;
import kianamod.powers.TemporalRiftPower;
import kianamod.powers.HonkaiEnergy;

/**
 * 琪亚娜的梦境事件
 * 琪亚娜陷入了深沉的梦境，与内心的自己对话。
 * 三个选项对应三个形态的祝福。
 */
public class KianaDreamEvent extends AbstractImageEvent {
    public static final String ID = "ExampleMod:KianaDreamEvent";
    private static final String NAME = "琪亚娜的梦境";
    private static final String DESC = "你站在一片纯白空间中。三个琪亚娜的身影出现在你面前——"
            + "火焰中的战士、虚空中的女王、以及光芒中的终焉。";
    private static final String[] OPTIONS = {
            "拥抱薪炎（获得2层\u201C不灭薪炎\u201D）",
            "踏入虚空（获得2层\u201C时空裂隙\u201D）",
            "迈向终焉（获得1层崩坏能 + 回复8点生命）"
    };

    private int screenNum = 0;

    public KianaDreamEvent() {
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
                    case 0: // 拥抱薪炎
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new EternalBlazingFlamePower(
                                                AbstractDungeon.player, AbstractDungeon.player, 2), 2));
                        this.imageEventText.updateBodyText(
                                "薪炎之力涌入你的体内。你感受到了火焰战士的意志。");
                        break;

                    case 1: // 踏入虚空
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new TemporalRiftPower(AbstractDungeon.player, 2), 2));
                        this.imageEventText.updateBodyText(
                                "虚空之力在你身边流转。你感受到了空之律者的力量。");
                        break;

                    case 2: // 迈向终焉
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new HonkaiEnergy(AbstractDungeon.player, 1), 1));
                        AbstractDungeon.player.heal(8);
                        this.imageEventText.updateBodyText(
                                "终焉的光芒照耀着你。你感受到了无尽的可能。");
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
