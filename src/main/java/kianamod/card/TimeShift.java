package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.TemporalRiftPower;
import kianamod.util.FlameCardUtils;

/**
 * 时空错位 (TimeShift)
 * 获得 2(3) 层时空裂隙。获得 2 崩坏能。
 */
public class TimeShift extends CustomCard {
    public static final String ID = "ExampleMod:TimeShift";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/TimeShift.png";

    private static final int COST = 1;
    private static final int BASE_RIFT = 2;
    private static final int UPG_RIFT = 3;
    private static final int HONKAI = 2;

    public TimeShift() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_RIFT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new TemporalRiftPower(p, this.magicNumber), this.magicNumber));
        FlameCardUtils.addHonkai(p, HONKAI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_RIFT - BASE_RIFT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TimeShift();
    }
}
