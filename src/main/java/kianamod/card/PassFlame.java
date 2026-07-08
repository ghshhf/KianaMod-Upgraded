package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 薪火相传 (PassFlame)
 * 获得等于当前崩坏能层数 2(3) 倍的格挡；获得 1 点崩坏能。
 */
public class PassFlame extends CustomCard {
    public static final String ID = "ExampleMod:PassFlame";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/PassFlame.png";

    private static final int COST = 1;
    private static final int MULT = 2;
    private static final int UPG_MULT = 3;
    private static final int HONKAI = 1;

    public PassFlame() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = MULT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int honkai = FlameCardUtils.getHonkai(p);
        int block = honkai * this.magicNumber;
        addToBot(new GainBlockAction(p, p, block));
        FlameCardUtils.addHonkai(p, HONKAI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_MULT - MULT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PassFlame();
    }
}
