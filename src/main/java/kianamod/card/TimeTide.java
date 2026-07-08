package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import kianamod.characters.MyCharacter;
import kianamod.powers.TemporalRiftPower;

/**
 * 轮回时澜 (TimeTide)
 * 费用 1 | RARE | 能力 | 自身
 * 每回合开始时，获得 2(3) 层时空裂隙。
 * 原作概念：积累轮回能量，时间之力的持续汇聚。
 */
public class TimeTide extends CustomCard {
    public static final String ID = "ExampleMod:TimeTide";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/TimeTide.png";

    private static final int COST = 1;
    private static final int BASE_AMT = 2;
    private static final int UPG_AMT = 3;

    public TimeTide() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = BASE_AMT;
        this.magicNumber = this.baseMagicNumber;
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TimeTidePower(p, this.magicNumber), this.magicNumber));
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
        return new TimeTide();
    }

    /**
     * 轮回时澜能力：每回合开始时获得 TemporalRiftPower。
     */
    public static class TimeTidePower extends AbstractPower {
        public static final String POWER_ID = "ExampleMod:TimeTidePower";

        public TimeTidePower(AbstractCreature owner, int amount) {
            this.name = "轮回时澜";
            this.ID = POWER_ID;
            this.owner = owner;
            this.amount = amount;
            this.type = PowerType.BUFF;
            this.isTurnBased = false;
            this.updateDescription();
        }

        @Override
        public void updateDescription() {
            this.description = "每回合开始时，获得 #y" + this.amount + " 层 #y时空裂隙 。";
        }

        @Override
        public void atStartOfTurn() {
            if (this.owner == null || this.owner.isDeadOrEscaped()) {
                return;
            }
            flash();
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(this.owner, this.owner,
                            new TemporalRiftPower(this.owner, this.amount), this.amount));
        }
    }
}
