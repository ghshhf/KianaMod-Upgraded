package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.EvasionPower;

/**
 * 维度放逐 (DimensionBanish)
 * 造成 10(14) 点伤害。若处于空之律者形态，获得 2(3) 层闪避。
 */
public class DimensionBanish extends CustomCard {
    public static final String ID = "ExampleMod:DimensionBanish";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/DimensionBanish.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 10;
    private static final int UPG_DAMAGE = 14;
    private static final int BASE_EVASION = 2;
    private static final int UPG_EVASION = 3;

    public DimensionBanish() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = BASE_EVASION;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (p.stance != null && p.stance.ID.equals("ExampleMod:SpaceStance")) {
            addToBot(new ApplyPowerAction(p, p, new EvasionPower(p, this.magicNumber), this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE - BASE_DAMAGE);
            this.upgradeMagicNumber(UPG_EVASION - BASE_EVASION);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new DimensionBanish();
    }
}
