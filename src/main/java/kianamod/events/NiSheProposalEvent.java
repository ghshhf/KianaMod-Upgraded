package kianamod.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import kianamod.powers.HonkaiEnergy;
import kianamod.events.AlliancePower;

/**
 * 逆熵的合作提案事件
 * 逆熵的代理人向你提出了一个交易。
 */
public class NiSheProposalEvent extends AbstractImageEvent {
    public static final String ID = "ExampleMod:NiSheProposalEvent";
    private static final String NAME = "逆熵的合作提案";
    private static final String DESC = "逆熵的使者递给你一份文件，上面写着\u201C合作共赢\u201D。";
    private static final String[] OPTIONS = {
            "接受合作：强化装甲（失去5点生命，本场战斗获得崩坏能时额外+1）",
            "接受合作：资金援助（获得120金币，失去1张随机卡牌）",
            "拒绝合作（获得1层崩坏能）"
    };

    private int screenNum = 0;

    public NiSheProposalEvent() {
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
                    case 0: // 接受合作：强化装甲
                        AbstractDungeon.player.damage(
                                new DamageInfo(null, 5, DamageInfo.DamageType.HP_LOSS));
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new AlliancePower(AbstractDungeon.player), 1));
                        this.imageEventText.updateBodyText(
                                "逆熵的科技强化了你的装甲。在本场战斗中，每次获得崩坏能时将额外获得1点。");
                        break;

                    case 1: // 接受合作：资金援助
                        AbstractDungeon.player.gainGold(120);
                        CardGroup deck = AbstractDungeon.player.masterDeck;
                        if (deck.size() > 0) {
                            CardGroup removable = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                            for (AbstractCard c : deck.group) {
                                if (c.rarity != AbstractCard.CardRarity.BASIC &&
                                        !c.hasTag(AbstractCard.CardTags.STARTER_STRIKE) &&
                                        !c.hasTag(AbstractCard.CardTags.STARTER_DEFEND)) {
                                    removable.addToTop(c);
                                }
                            }
                            if (removable.size() > 0) {
                                removable.shuffle();
                                AbstractCard cardToRemove = removable.getNCardFromTop(0);
                                deck.removeCard(cardToRemove);
                                this.imageEventText.updateBodyText(
                                        "你获得了资金援助，但作为交换，失去了一张卡牌。");
                            } else {
                                deck.shuffle();
                                AbstractCard cardToRemove = deck.getNCardFromTop(0);
                                deck.removeCard(cardToRemove);
                                this.imageEventText.updateBodyText(
                                        "你获得了资金援助，但作为交换，失去了一张基础卡牌。");
                            }
                        } else {
                            this.imageEventText.updateBodyText("你获得了资金援助。");
                        }
                        break;

                    case 2: // 拒绝合作
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new HonkaiEnergy(AbstractDungeon.player, 1), 1));
                        this.imageEventText.updateBodyText(
                                "你拒绝了逆熵的提案，依靠自己的力量继续前进。");
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
