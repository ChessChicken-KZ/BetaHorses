package kz.chesschicken.betahorses.entity

import kz.chesschicken.betahorses.init.BetaHorsesListener
import kz.chesschicken.betahorses.mixin.AccessorLiving
import kz.chesschicken.betahorses.mixin.AccessorMonsterBase
import net.minecraft.block.BlockBase
import net.minecraft.entity.EntityBase
import net.minecraft.entity.Living
import net.minecraft.entity.animal.AnimalBase
import net.minecraft.entity.monster.MonsterBase
import net.minecraft.entity.player.PlayerBase
import net.minecraft.item.ItemBase
import net.minecraft.item.ItemInstance
import net.minecraft.level.Level
import net.minecraft.util.io.CompoundTag
import net.minecraft.util.maths.MathHelper
import org.lwjgl.input.Keyboard


open class EntityHorse(l: Level) : AnimalBase(l) {
    var lvl = 0
    var size = 0f
    var speedBase = 0f
    var sprintCount = 0
    var sprintMax = 0
    var sprintCD = false
    var canMove = false
    var owner = ""

    init {
        size = 1.0F
        speedBase = 0.5F
        sprintCount = 0
        sprintMax = 100
        sprintCD = false
        canMove = false
        setSize(size, size * 1.5F)


        lvl = rand.nextInt(6)
        if(lvl == 5)
        {
            if(rand.nextBoolean()) lvl--
        }

        health = getMaxHealth()

        init()
    }

    private fun init() {
        field_1641 = 1.0f
        when (lvl) {
            0 -> texture = "/assets/betahorses/textures/horse/horseDarkBrown.png"
            1 -> {
                speedBase = 0.8f
                texture = "/assets/betahorses/textures/horse/horseBrown.png"
            }
            2 -> {
                field_1641 = 3f
                texture = "/assets/betahorses/textures/horse/horseLightBrown.png"
            }
            3 -> {
                speedBase = 0.6f
                sprintMax = 125
                field_1641 = 2.0f
                texture = "/assets/betahorses/textures/horse/horseWhite.png"
            }
            4 -> {
                texture = "/assets/betahorses/textures/horse/horseBlack.png"
                sprintMax = 175
            }
            5 -> {
                speedBase = 1.0f
                field_1641 = 3f
                texture = "/assets/betahorses/textures/horse/horseRainbow.png"
                sprintMax = 250
            }
        }
        setSize(size, size * 1.5f)
    }

    override fun damage(target: EntityBase?, amount: Int): Boolean {
        return if(target != null)
            false
        else
            super.damage(target, amount)

    }

    override fun handleFallDamage(height: Float) {
        var h1 = height
        h1 /= 2.0f
        super.handleFallDamage(h1)
    }

    private fun getMaxHealth(): Int {
        when (lvl) {
            0 -> return 40
            3 -> return 25
            5 -> return 70
        }
        return 15
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(16, java.lang.Byte.valueOf(0.toByte()))
    }

    override fun writeCustomDataToTag(tag: CompoundTag?) {
        super.writeCustomDataToTag(tag!!)
        tag.put("owner", owner)
        tag.put("Saddle", getSaddled())
        tag.put("canMove", canMove)
        tag.put("lvl", lvl)
        tag.put("size", size)
    }

    override fun readCustomDataFromTag(tag: CompoundTag?) {
        super.readCustomDataFromTag(tag!!)
        owner = tag.getString("owner")
        setSaddled(tag.getBoolean("Saddle"), owner)
        canMove = tag.getBoolean("canMove")
        lvl = tag.getInt("lvl")
        size = tag.getFloat("size")

        init()
    }


    override fun interact(entityplayer: PlayerBase?): Boolean {
        if (entityplayer!!.method_1373())
        {
            canMove = !canMove
            return true
        }
        if (entityplayer.inventory.heldItem != null) {
            if (entityplayer.inventory.heldItem.type == ItemBase.wheat) {
                if (health >= getMaxHealth()) {
                    return super.interact(entityplayer)
                }
                return healHorse(5, entityplayer)
            } else if(entityplayer.inventory.heldItem.type == BetaHorsesListener.blockHay)
            {
                if (health >= getMaxHealth()) {
                    return super.interact(entityplayer)
                }
                return healHorse(getMaxHealth(), entityplayer)
            }
            else {
                return mount(entityplayer)
            }
        }
        return if (getSaddled() && !level.isClient && (passenger == null || passenger == entityplayer) && entityplayer.name.equals(owner)) {
            entityplayer.startRiding(this)
            true
        } else false

    }
    private fun healHorse(int: Int, p: PlayerBase): Boolean
    {


        if (p.inventory.decreaseAmountOfItem(ItemBase.wheat.id)) {
            for (i in 3 downTo 1) {
                level.addParticle(
                    "heart", x + rand.nextFloat().toDouble() - 0.5, y + 0.5, z + rand.nextFloat()
                        .toDouble() - 0.5, 1.0, 1.0, 1.0
                )
            }
            addHealth(int)
            return true
        }
        return false
    }

    private fun mount(p: PlayerBase): Boolean {
        if (getSaddled() && !level.isClient && p.name.equals(owner)) {
            p.startRiding(this)
            return true
        }
        return if (size >= 1.0f && p.inventory.containsItem(ItemInstance(ItemBase.saddle)) && !getSaddled()) {
            p.inventory.decreaseAmountOfItem(ItemBase.saddle.id)
            setSaddled(true, p.name)
            false
        } else false
    }

    override fun getMountedHeightOffset(): Double {
        return height - 0.30000001192092896
    }

    override fun method_920() {
        if(getSaddled())
        {
            if(removed)
                super.method_920()
        }
        else super.method_920()
    }

    override fun getMobDrops(): Int {

        return if (method_1359()) {
            if(getSaddled())
                BetaHorsesListener.blockHay!!.id
            else
                ItemBase.cookedPorkchop.id
        } else {
            if(getSaddled())
                ItemBase.saddle.id
            else
                ItemBase.leather.id
        }

    }

    /**
     * Get info about horse.
     * @return saddled or not.
     */
    private fun getSaddled(): Boolean {
        return (dataTracker.getByte(16).toInt() and 1) != 0
    }

    private fun setSaddled(flag: Boolean, nickname: String) {
        if (flag) {
            dataTracker.setInt(16, java.lang.Byte.valueOf(1.toByte()))
        } else {
            dataTracker.setInt(16, java.lang.Byte.valueOf(0.toByte()))
        }
        owner = nickname
    }

    override fun canSpawn(): Boolean {
        val i: Int = MathHelper.floor(x)
        val j: Int = MathHelper.floor(boundingBox.minY)
        val k: Int = MathHelper.floor(z)
        return level.getTileId(i, j - 1, k) == BlockBase.GRASS.id || level.getTileId(i, j - 1, k) == BlockBase.SNOW.id

    }


    override fun updateDespawnCounter() {
        if (lvl == 5) {
            level.addParticle(
                "splash", x + rand.nextFloat().toDouble() - 0.5, y + 1.0, z + rand.nextFloat()
                    .toDouble() - 0.5, rand.nextDouble(), rand.nextDouble(), rand.nextDouble()
            )
        }
        if (size < 1.2) {
            size += 0.01f
        }
        if (sprintCount != 0) {
            if (sprintCount >= sprintMax) {
                sprintCD = true
            }
            sprintCount -= sprintMax / 100
        } else {
            sprintCD = false
        }
        if (passenger != null) {
            //newPosRotationIncrements = 0
            movementSpeed = speedBase
            val entityliving: Living = passenger as Living
            if (passenger is PlayerBase) {
                if (field_1029 < 1.0f && (entityliving as AccessorLiving).field_1029() > 0.0f) {
                    field_1029 += 0.02f
                }
                if ((entityliving as AccessorLiving).field_1029() <= 0.0f && field_1029 > -1f) {
                    field_1029 -= 0.02f
                }
                if ((entityliving as AccessorLiving).field_1029() == 0.0f) {
                    if (field_1029 < 0.0f) {
                        field_1029 += 0.029999999999999999f
                        if (field_1029 > 0.0f) {
                            field_1029 = 0.0f
                        }
                    } else if (field_1029 > 0.0f) {
                        field_1029 -= 0.029999999999999999f
                        if (field_1029 < 0.0f) {
                            field_1029 = 0.0f
                        }
                    }
                }
                if (field_1029 >= 1.0f && entityliving.method_1373() && !sprintCD) {
                    sprintCount += 2
                    //entityliving.addPotionEffect(PotionEffect(Potion.moveSpeed.id, 2, 2))
                    if (field_1029 < 1.7) {
                        field_1029 += 0.03f
                    }
                } else if (field_1029 > 1.0f) {
                    field_1029 -= 0.10000000000000001f
                }
                velocityZ = (MathHelper.cos(Math.toRadians(entityliving.yaw.toDouble()).toFloat()) * (field_1029 * movementSpeed)).toDouble()
                velocityX = (-MathHelper.sin(Math.toRadians(entityliving.yaw.toDouble()).toFloat()) * (field_1029 * movementSpeed)).toDouble()
                if (Keyboard.isKeyDown(57) && (onGround || field_1612) && passenger is PlayerBase && velocityY < 0.69999998807907104) {
                    velocityY++
                }
                yaw = entityliving.yaw
                prevYaw = entityliving.yaw
            } else if (passenger is MonsterBase) {
                if (field_1029 < 1.3999999999999999 && (entityliving as AccessorLiving).field_1029() > 0.0f) {
                    field_1029 += 0.03f
                }
                if ((entityliving as AccessorLiving).field_1029() <= 0.0f && field_1029 > -1f) {
                    field_1029 -= 0.03f
                }
                if ((entityliving as AccessorLiving).field_1029() == 0.0f) {
                    if (field_1029 < 0.0f) {
                        field_1029 += 0.029999999999999999f
                        if (field_1029 > 0.0f) {
                            field_1029 = 0.0f
                        }
                    } else if (field_1029 > 0.0f) {
                        field_1029 -= 0.029999999999999999f
                        if (field_1029 < 0.0f) {
                            field_1029 = 0.0f
                        }
                    }
                }
                val f: Float = (entityliving.yaw - yaw) / 15f
                if (field_1029 >= 0.80000000000000004 && rand.nextInt(16) == 0 && ((passenger as MonsterBase) as AccessorMonsterBase).attackTarget() != null) {
                    if (((passenger as MonsterBase) as AccessorMonsterBase).attackTarget().passenger != null) {
                        ((passenger as MonsterBase) as AccessorMonsterBase).attackTarget().passenger as Living
                        if ((entityliving as AccessorLiving).field_1029() < 0.5) {
                            field_1029 /= 3f
                        }
                    } else {
                        field_1029 /= 3f
                    }
                }
                yaw += f
                velocityZ = (MathHelper.cos(Math.toRadians(yaw.toDouble()).toFloat()) * (field_1029 * movementSpeed)).toDouble()
                velocityX = (-MathHelper.sin(Math.toRadians(yaw.toDouble()).toFloat()) * (field_1029 * movementSpeed)).toDouble()
            }
            if (!onGround) {
                velocityY -= 0.20000000000000001
            }
            if (field_1612) {
                if (rand.nextInt(2) == 0) {
                    velocityY += 0.40000000596046448
                }
                velocityX /= 3.0
                velocityZ /= 3.0
            }
            travel(field_1029, 0.0f)
            field_1048 = limbDistance
            val d: Double = x - prevX
            val d1: Double = z - prevZ
            var f1: Float = MathHelper.sqrt(d * d + d1 * d1) * 4f
            if (f1 > 1.0f) {
                f1 = 1.0f
            }
            limbDistance += (f1 - limbDistance) * 0.4f
            field_1050 += limbDistance
        } else {
            super.updateDespawnCounter()
        }
    }


}