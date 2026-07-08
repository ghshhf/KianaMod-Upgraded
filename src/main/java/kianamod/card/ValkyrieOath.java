package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.EternalBlazingFlamePower;

/**
 * 女武神的誓言 (ValkyrieOath)
 * 获得 2(3) 层永燃的薪火。
 */
public class ValkyrieOath extends CustomCard {
    public static final String ID = "ExampleMod:ValkyrieOath";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/ValkyrieOath.png";

    private static final int COST = 1;
    private static final int BASE_AMT = 2;
    private static final int UPG_AMT = 3;

    public ValkyrieOath() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = BASE_AMT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EternalBlazingFlamePower(p, p, this.magicNumber), this.magicNumber));
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
        return new ValkyrieOath();
    }
}
