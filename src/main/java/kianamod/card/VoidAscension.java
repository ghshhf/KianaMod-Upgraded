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
 * 虚界升变 (VoidAscension)
 * 费用 0 | UNCOMMON | 技能 | 自身 | 保留
 * 获得 1 崩坏能。若处于终焉之律者形态，额外获得 1(2) 崩坏能。
 * 原作概念：虚数属性转化，终焉形态下增益翻倍。
 */
public class VoidAscension extends CustomCard {
    public static final String ID = "ExampleMod:VoidAscension";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/VoidAscension.png";

    private static final int COST = 0;
    private static final int BASE_HONKAI = 1;
    private static final int BASE_EXTRA = 1;
    private static final int UPG_EXTRA = 2;

    public VoidAscension() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_EXTRA;
        this.magicNumber = this.baseMagicNumber;
        this.retain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int total = BASE_HONKAI;
        if (p.stance != null && p.stance.ID.equals("ExampleMod:EndStance")) {
            total += this.magicNumber;
        }
        FlameCardUtils.addHonkai(p, total);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_EXTRA - BASE_EXTRA);
            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new VoidAscension();
    }
}
