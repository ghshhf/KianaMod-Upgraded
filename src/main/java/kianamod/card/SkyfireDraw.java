package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.util.FlameCardUtils;

/**
 * 天火出鞘 (SkyfireDraw)
 * 对所有敌人造成 14(18) 点火焰伤害；若崩坏能大于等于 5 点，改为造成 20(26) 点伤害；获得 2 点崩坏能。
 */
public class SkyfireDraw extends CustomCard {
    public static final String ID = "ExampleMod:SkyfireDraw";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/SkyfireDraw.png";

    private static final int COST = 2;
    private static final int BASE_DAMAGE = 14;
    private static final int UPG_DAMAGE = 18;
    private static final int BIG_DAMAGE = 20;
    private static final int UPG_BIG_DAMAGE = 26;
    private static final int HONKAI_THRESHOLD = 5;
    private static final int HONKAI = 2;

    public SkyfireDraw() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = BIG_DAMAGE;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int hit = (FlameCardUtils.getHonkai(p) >= HONKAI_THRESHOLD) ? this.magicNumber : this.damage;
        addToBot(new DamageAllEnemiesAction(p, hit, DamageInfo.DamageType.NORMAL,
                AbstractGameAction.AttackEffect.FIRE));
        FlameCardUtils.addHonkai(p, HONKAI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE - BASE_DAMAGE);
            this.upgradeMagicNumber(UPG_BIG_DAMAGE - BIG_DAMAGE);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkyfireDraw();
    }
}
