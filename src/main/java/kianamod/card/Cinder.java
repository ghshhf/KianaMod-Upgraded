package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 余烬 (Cinder)
 * 获得 1(2) 点崩坏能。保留。
 */
public class Cinder extends CustomCard {
    public static final String ID = "ExampleMod:Cinder";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/Cinder.png";

    private static final int COST = 0;
    private static final int BASE_AMT = 1;
    private static final int UPG_AMT = 2;

    public Cinder() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_AMT;
        this.magicNumber = this.baseMagicNumber;
        this.retain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        FlameCardUtils.addHonkai(p, this.magicNumber);
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
        return new Cinder();
    }
}
