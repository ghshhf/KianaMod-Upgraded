package kianamod.card;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kianamod.characters.MyCharacter;
import kianamod.powers.AcceleratePower;
import kianamod.util.FlameCardUtils;

/**
 * 月锋姿态 (MoonStance)
 * 费用 1 | UNCOMMON | 技能 | 自身
 * 获得 2(3) 崩坏能。获得 2(3) 层加速。
 * 原作概念：月锋姿态（空中型），敏捷爆发。
 */
public class MoonStance extends CustomCard {
    public static final String ID = "ExampleMod:MoonStance";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = CARD_STRINGS.NAME;
    public static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final String IMG = "ExampleModResources/images/MoonStance.png";

    private static final int COST = 1;
    private static final int BASE_AMT = 2;
    private static final int UPG_AMT = 3;

    public MoonStance() {
        super(ID, NAME, IMG, COST, DESCRIPTION, CardType.SKILL,
                MyCharacter.PlayerColorEnum.EXAMPLE_AZURE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = BASE_AMT;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amt = this.magicNumber;
        FlameCardUtils.addHonkai(p, amt);
        addToBot(new ApplyPowerAction(p, p, new AcceleratePower(p, amt), amt));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_AMT - BASE_AMT);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MoonStance();
    }
}
