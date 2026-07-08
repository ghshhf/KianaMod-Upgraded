package kianamod.card;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import kianamod.characters.MyCharacter;

/**
 * 星轨 (StellarOrbit)
 * 每回合首次打出卡牌时，造成 6(9) 点伤害给随机敌人。
 */
public class StellarOrbit extends CustomCard {
    public static final String ID = "ExampleMod:StellarOrbit";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/StellarOrbit.png";

    private static final int COST = 1;
    private static final int BASE_TRIGGER_DMG = 6;
    private static final int UPG_TRIGGER_DMG = 9;

    public StellarOrbit() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = BASE_TRIGGER_DMG;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new com.megacrit.cardcrawl.actions.common.ApplyPowerAction(p, p,
                new StellarOrbitPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_TRIGGER_DMG - BASE_TRIGGER_DMG);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new StellarOrbit();
    }

    /**
     * 星轨能力：每回合首次打出卡牌时，对随机敌人造成伤害。
     */
    public static class StellarOrbitPower extends AbstractPower {
        public static final String POWER_ID = "ExampleMod:StellarOrbitPower";
        private boolean activatedThisTurn = false;

        public StellarOrbitPower(AbstractPlayer owner, int amount) {
            this.ID = POWER_ID;
            this.name = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
            this.owner = owner;
            this.amount = amount;
            this.type = PowerType.BUFF;
            this.updateDescription();
            this.loadRegion("precision");
        }

        @Override
        public void updateDescription() {
            this.description = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS[0]
                    .replace("!M!", String.valueOf(this.amount));
        }

        @Override
        public void atStartOfTurn() {
            this.activatedThisTurn = false;
            this.updateDescription();
        }

        @Override
        public void onAfterCardPlayed(AbstractCard card) {
            if (!this.activatedThisTurn) {
                this.activatedThisTurn = true;
                AbstractMonster target = AbstractDungeon.getRandomMonster();
                if (target != null && !target.isDead && !target.isDying) {
                    this.flash();
                    AbstractDungeon.actionManager.addToBottom(
                            new DamageAction(target,
                                    new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS),
                                    AbstractGameAction.AttackEffect.SLASH_HEAVY));
                }
            }
        }
    }
}
