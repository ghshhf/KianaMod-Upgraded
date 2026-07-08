package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.BurnPower;
import kianamod.util.FlameCardUtils;

/**
 * 燎原 (PrairieFire)
 * 对所有敌人施加 3(4) 层火焰元素伤害；获得 1 点崩坏能。
 */
public class PrairieFire extends CustomCard {
    public static final String ID = "ExampleMod:PrairieFire";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/PrairieFire.png";

    private static final int COST = 1;
    private static final int BASE_BURN = 3;
    private static final int UPG_BURN = 4;
    private static final int HONKAI = 1;

    public PrairieFire() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = BASE_BURN;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters) {
            if (mo != null && !mo.isDeadOrEscaped()) {
                addToBot(new ApplyPowerAction(mo, p, new BurnPower(mo, p, this.magicNumber), this.magicNumber));
            }
        }
        FlameCardUtils.addHonkai(p, HONKAI);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_BURN - BASE_BURN);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrairieFire();
    }
}
