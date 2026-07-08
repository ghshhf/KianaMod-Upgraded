package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 空间折叠 (SpaceFold)
 * 获得 3(4) 崩坏能。抽 2(3) 张牌。
 */
public class SpaceFold extends CustomCard {
    public static final String ID = "ExampleMod:SpaceFold";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/SpaceFold.png";

    private static final int COST = 1;
    private static final int BASE_HONKAI = 3;
    private static final int UPG_HONKAI = 4;
    private static final int BASE_DRAW = 2;
    private static final int UPG_DRAW = 3;

    public SpaceFold() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_HONKAI;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        FlameCardUtils.addHonkai(p, this.magicNumber);
        int drawAmt = this.upgraded ? UPG_DRAW : BASE_DRAW;
        addToBot(new DrawCardAction(p, drawAmt));
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
        return new SpaceFold();
    }
}
