package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 炎龙破 (FlameDragonBreak)
 * 造成 16(21) 点火焰伤害；然后消耗全部崩坏能，每消耗 1 点额外造成 2(3) 点火焰元素伤害。
 */
public class FlameDragonBreak extends CustomCard {
    public static final String ID = "ExampleMod:FlameDragonBreak";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/FlameDragonBreak.png";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 16;
    private static final int UPG_DAMAGE = 21;
    private static final int BASE_BONUS = 2;
    private static final int UPG_BONUS = 3;

    public FlameDragonBreak() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = BASE_BONUS;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.FIRE));
        int n = FlameCardUtils.getHonkai(p);
        for (int i = 0; i < n; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.magicNumber, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.FIRE));
        }
        if (n > 0) {
            FlameCardUtils.reduceHonkai(p, n);
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE - BASE_DAMAGE);
            this.upgradeMagicNumber(UPG_BONUS - BASE_BONUS);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FlameDragonBreak();
    }
}
