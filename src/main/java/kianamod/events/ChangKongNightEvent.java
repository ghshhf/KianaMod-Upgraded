package kianamod.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import kianamod.powers.HonkaiEnergy;

/**
 * 长空市之夜事件
 * 夜间的长空市，崩坏兽正在袭击城市。
 */
public class ChangKongNightEvent extends AbstractImageEvent {
    public static final String ID = "ExampleMod:ChangKongNightEvent";
    private static final String NAME = "长空市之夜";
    private static final String DESC = "夜晚的长空市陷入混乱，崩坏兽在街道上游荡。你握紧了武器。";
    private static final String[] OPTIONS = {
            "正面迎击崩坏兽（战斗，额外获得3层崩坏能）",
            "救助被困平民（回复10点生命）",
            "寻找补给（获得1瓶随机药水 + 75金币）"
    };

    private int screenNum = 0;

    public ChangKongNightEvent() {
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
                    case 0: // 正面迎击崩坏兽
                        // Give combat rewards directly (gold + card) + bonus HonkaiEnergy
                        AbstractDungeon.player.gainGold(AbstractDungeon.miscRng.random(25, 45));
                        // Give a random attack card as fight reward
                        AbstractCard fightReward = getRandomCombatCard();
                        if (fightReward != null) {
                            AbstractDungeon.effectList.add(
                                    new ShowCardAndObtainEffect(fightReward,
                                            Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f));
                        }
                        // Bonus HonkaiEnergy
                        AbstractDungeon.actionManager.addToBottom(
                                new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                        new HonkaiEnergy(AbstractDungeon.player, 3), 3));
                        this.imageEventText.updateBodyText("你击退了崩坏兽群，从废墟中找到了战利品，崩坏能也变得更加充沛。");
                        break;

                    case 1: // 救助被困平民
                        int honkaiAmount = 0;
                        if (AbstractDungeon.player.hasPower(HonkaiEnergy.POWER_ID)) {
                            honkaiAmount = AbstractDungeon.player.getPower(HonkaiEnergy.POWER_ID).amount;
                        }
                        if (honkaiAmount >= 3) {
                            // 崩坏能≥3：回复15点生命，不消耗崩坏能
                            AbstractDungeon.player.heal(15);
                            this.imageEventText.updateBodyText(
                                    "你运用崩坏能的力量迅速清除了障碍，成功救出了被困的平民。身心俱佳。");
                        } else {
                            // 回复10点生命，失去1层崩坏能（如果拥有崩坏能）
                            AbstractDungeon.player.heal(10);
                            if (honkaiAmount > 0) {
                                AbstractDungeon.actionManager.addToBottom(
                                        new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player,
                                                HonkaiEnergy.POWER_ID, 1));
                            }
                            this.imageEventText.updateBodyText(
                                    "你耗费了一些体力救出了平民。虽然有些疲惫，但内心充实。");
                        }
                        break;

                    case 2: // 寻找补给
                        AbstractDungeon.player.gainGold(75);
                        AbstractPotion potion = PotionHelper.getRandomPotion();
                        if (potion != null) {
                            AbstractDungeon.player.obtainPotion(potion);
                        }
                        this.imageEventText.updateBodyText("你在废墟中找到了一些补给品和金币。");
                        break;
                }
                screenNum = 1;
                break;

            case 1:
                this.openMap();
                break;
        }
    }

    private AbstractCard getRandomCombatCard() {
        java.util.ArrayList<AbstractCard> eligibleCards = new java.util.ArrayList<>();
        for (AbstractCard c : CardLibrary.getAllCards()) {
            if (c.rarity != AbstractCard.CardRarity.SPECIAL &&
                    c.rarity != AbstractCard.CardRarity.BASIC &&
                    c.color == AbstractDungeon.player.getCardColor()) {
                eligibleCards.add(c);
            }
        }
        if (eligibleCards.size() > 0) {
            int idx = AbstractDungeon.miscRng.random(eligibleCards.size() - 1);
            return eligibleCards.get(idx).makeCopy();
        }
        return null;
    }
}
