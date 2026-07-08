package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.AcceleratePower;
import kianamod.powers.EvasionPower;

/**
 * 亚空行者 (SubspaceWalker)
 * 获得 2(3) 层加速。获得 2(3) 层闪避。
 */
public class SubspaceWalker extends CustomCard {
    public static final String ID = "ExampleMod:SubspaceWalker";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/SubspaceWalker.png";

    private static final int COST = 1;
    private static final int BASE_AMOUNT = 2;
    private static final int UPG_AMOUNT = 3;

    public SubspaceWalker() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.POWER,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = BASE_AMOUNT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AcceleratePower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new EvasionPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_AMOUNT - BASE_AMOUNT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SubspaceWalker();
    }
}
