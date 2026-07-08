package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;

/**
 * 虚空汲取 (VoidDrain)
 * 造成 8(12) 点伤害。获得已造成伤害值一半的格挡（四舍五入）。
 */
public class VoidDrain extends CustomCard {
    public static final String ID = "ExampleMod:VoidDrain";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/VoidDrain.png";

    private static final int COST = 1;
    private static final int BASE_DAMAGE = 8;
    private static final int UPG_DAMAGE = 12;

    public VoidDrain() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.ATTACK,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = BASE_DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),
                AbstractGameAction.AttackEffect.SLASH_HEAVY));
        // 格挡 = 伤害 / 2（四舍五入）
        int blockAmount = (this.damage + 1) / 2;
        addToBot(new GainBlockAction(p, p, blockAmount));
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
        return new VoidDrain();
    }
}
