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

/**
 * 跨越终焉的破决 (FinalResolution)
 * 费用 3 | RARE | 攻击 | 全体敌人 | 消耗
 * 对所有敌人造成 15(20) 火焰伤害 3 次。消耗。
 * 原作概念：终焉必杀技连携爆发，三段式毁灭攻击。
 */
public class FinalResolution extends CustomCard {
    public static final String ID = "ExampleMod:FinalResolution";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/FinalResolution.png";

    private static final int COST = 3;
    private static final int BASE_DAMAGE = 15;
    private static final int UPG_DAMAGE = 20;
    private static final int HITS = 3;

    public FinalResolution() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = BASE_DAMAGE;
        this.baseMagicNumber = HITS;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < HITS; i++) {
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage,
                    DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPG_DAMAGE - BASE_DAMAGE);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new FinalResolution();
    }
}
