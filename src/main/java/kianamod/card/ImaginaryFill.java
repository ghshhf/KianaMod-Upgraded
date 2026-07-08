package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 虚数填充 (ImaginaryFill)
 * 获得 4(6) 崩坏能。获得 1 层脆弱。
 */
public class ImaginaryFill extends CustomCard {
    public static final String ID = "ExampleMod:ImaginaryFill";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/ImaginaryFill.png";

    private static final int COST = 0;
    private static final int BASE_HONKAI = 4;
    private static final int UPG_HONKAI = 6;
    private static final int VULN = 1;

    public ImaginaryFill() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_HONKAI;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        FlameCardUtils.addHonkai(p, this.magicNumber);
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(p, VULN, false), VULN));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_HONKAI - BASE_HONKAI);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ImaginaryFill();
    }
}
