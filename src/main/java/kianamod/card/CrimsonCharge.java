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
 * 赤焰冲锋 (CrimsonCharge)
 * 造成 6(8) 点火焰伤害 3(4) 次；获得 2 点崩坏能。
 */
public class CrimsonCharge extends CustomCard {
    public static final String ID = "ExampleMod:CrimsonCharge";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/CrimsonCharge.png";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 6;
    private static final int UPG_DAMAGE = 8;
    private static final int BASE_TIMES = 3;
    private static final int UPG_TIMES = 4;
    private static final int HONKAI = 2;

    public CrimsonCharge() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = BASE_TIMES;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                    AbstractGameAction.AttackEffect.FIRE));
        }
        FlameCardUtils.addHonkai(p, HONKAI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE - BASE_DAMAGE);
            this.upgradeMagicNumber(UPG_TIMES - BASE_TIMES);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CrimsonCharge();
    }
}
