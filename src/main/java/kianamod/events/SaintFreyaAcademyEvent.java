package kianamod.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import kianamod.powers.HonkaiEnergy;

/**
 * 圣芙蕾雅学园事件
 * 琪亚娜在学园的训练场遇到了正在锻炼的芽衣和布洛妮娅。
 * 三个选项分别对应三条收益路径。
 */
public class SaintFreyaAcademyEvent extends AbstractImageEvent {
    public static final String ID = "ExampleMod:SaintFreyaAcademyEvent";
    private static final String NAME = "圣芙蕾雅学园";
    private static final String DESC = "你来到圣芙蕾雅学园的训练场。芽衣和布洛妮娅正在切磋。她们邀请你一起训练。";
    private static final String[] OPTIONS = {
            "与芽衣切磋剑术（失去3点生命，获得一张升级攻击牌）",
            "与布洛妮娅玩射击游戏（获得50金币 + 2崩坏能）",
            "去图书馆自习（回复5点生命，获得一张随机牌）"
    };
    private static final String[] MSG_AFTER = {
            "你和芽衣切磋了几个回合，虽然受了点轻伤，但剑术有所精进。",
            "你和布洛妮娅玩了几局射击游戏，赢得了一些奖金！",
            "你在图书馆安静地看了一会儿书，身心都得到了放松。"
    };

    private int screenNum = 0;

    public SaintFreyaAcademyEvent() {
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
                    case 0: // 与芽衣切磋剑术
                        AbstractDungeon.player.damage(
                                new DamageInfo(null, 3, DamageInfo.DamageType.HP_LOSS));
                        AbstractCard attackCard = getRandomAttackCard();
                        if (attackCard != null) {
                            attackCard.upgrade();
                            AbstractDungeon.effectList.add(
                                    new ShowCardAndObtainEffect(attackCard,
                                            Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        }
                        this.imageEventText.updateBodyText(MSG_AFTER[0]);
                        break;

                    case 1: // 与布洛妮娅玩射击游戏
                        AbstractDungeon.player.gainGold(50);
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new HonkaiEnergy(AbstractDungeon.player, 2), 2));
                        this.imageEventText.updateBodyText(MSG_AFTER[1]);
                        break;

                    case 2: // 去图书馆自习
                        AbstractDungeon.player.heal(5);
                        AbstractCard randomCard = getRandomCard();
                        if (randomCard != null) {
                            AbstractDungeon.effectList.add(
                                    new ShowCardAndObtainEffect(randomCard,
                                            Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        }
                        this.imageEventText.updateBodyText(MSG_AFTER[2]);
                        break;
                }
                screenNum = 1;
                break;

            case 1:
                this.openMap();
                break;
        }
    }

    private AbstractCard getRandomAttackCard() {
        CardGroup attackCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : CardLibrary.getAllCards()) {
            if (c.type == AbstractCard.CardType.ATTACK && c.rarity != AbstractCard.CardRarity.SPECIAL) {
                if (c.color == AbstractDungeon.player.getCardColor()) {
                    attackCards.addToTop(c);
                }
            }
        }
        if (attackCards.size() > 0) {
            attackCards.shuffle();
            return attackCards.getNCardFromTop(0).makeCopy();
        }
        return null;
    }

    private AbstractCard getRandomCard() {
        CardGroup eligible = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard c : CardLibrary.getAllCards()) {
            if (c.rarity != AbstractCard.CardRarity.SPECIAL &&
                    !c.hasTag(AbstractCard.CardTags.STARTER_DEFEND) &&
                    !c.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                if (c.color == AbstractDungeon.player.getCardColor()) {
                    eligible.addToTop(c);
                }
            }
        }
        if (eligible.size() > 0) {
            eligible.shuffle();
            return eligible.getNCardFromTop(0).makeCopy();
        }
        return null;
    }
}
