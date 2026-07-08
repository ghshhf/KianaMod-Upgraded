package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 时燧姿态 (BlazeStance)
 * 费用 1 | UNCOMMON | 技能 | 自身
 * 获得 2(3) 崩坏能。本回合获得 2(3) 点力量。
 * 原作概念：时燧姿态（地面型），增幅火焰伤害。
 */
public class BlazeStance extends CustomCard {
    public static final String ID = "ExampleMod:BlazeStance";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/BlazeStance.png";

    private static final int COST = 1;
    private static final int BASE_AMT = 2;
    private static final int UPG_AMT = 3;

    public BlazeStance() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_AMT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = this.magicNumber;
        // 获得崩坏能
        FlameCardUtils.addHonkai(p, amt);
        // 通过内联 Power 临时获得力量，回合结束时自动移除
        addToBot(new ApplyPowerAction(p, p, new BlazeStanceTempStrengthPower(p, amt), amt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_AMT - BASE_AMT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlazeStance();
    }

    /**
     * 时燧姿态的临时力量增益 Power。
     * 施加时给予力量，回合结束时移除力量并自毁。
     */
    public static class BlazeStanceTempStrengthPower extends AbstractPower {
        public static final String POWER_ID = "ExampleMod:BlazeStanceTempStrengthPower";

        public BlazeStanceTempStrengthPower(AbstractCreature owner, int amount) {
            this.name = "时燧之力";
            this.ID = POWER_ID;
            this.owner = owner;
            this.amount = amount;
            this.type = PowerType.BUFF;
            this.isTurnBased = true;
            this.updateDescription();
            // 在实例化时立刻应用力量（通过动作入队，与卡牌主效果在同一批次）
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
        }

        @Override
        public void updateDescription() {
            this.description = "本回合获得 #y" + this.amount + " 点 #y力量 。";
        }

        @Override
        public void atEndOfTurn(boolean isPlayer) {
            if (isPlayer && this.owner != null && !this.owner.isDeadOrEscaped()) {
                flash();
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, StrengthPower.POWER_ID));
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
    }
}
