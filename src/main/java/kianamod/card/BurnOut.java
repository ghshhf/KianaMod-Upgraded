package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.BurnOutPower;

/**
 * 燃尽 (BurnOut)
 * 获得燃尽能力：回合开始时，对自己施加 3(4) 点火焰元素伤害，并获得 2 点崩坏能。
 */
public class BurnOut extends CustomCard {
    public static final String ID = "ExampleMod:BurnOut";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/BurnOut.png";

    private static final int COST = 1;
    private static final int BASE_BURN = 3;
    private static final int UPG_BURN = 4;

    public BurnOut() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = BASE_BURN;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BurnOutPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_BURN - BASE_BURN);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BurnOut();
    }
}
