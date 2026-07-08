package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 烈焰风暴 (FlameStorm)
 * 对所有敌人造成 4(5) 点火焰伤害 3(4) 次；获得 1 点崩坏能。
 */
public class FlameStorm extends CustomCard {
    public static final String ID = "ExampleMod:FlameStorm";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/FlameStorm.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 4;
    private static final int UPG_DAMAGE = 5;
    private static final int BASE_TIMES = 3;
    private static final int UPG_TIMES = 4;
    private static final int HONKAI = 1;

    public FlameStorm() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = BASE_TIMES;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToBot(new DamageAllEnemiesAction(p, this.damage, DamageInfo.DamageType.NORMAL,
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
        return new FlameStorm();
    }
}
