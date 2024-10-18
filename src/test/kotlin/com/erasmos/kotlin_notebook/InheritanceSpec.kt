package com.erasmos.kotlin_notebook

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class InheritanceSpec : FreeSpec({

    "there are various types of membership, each of which allows different functionality, namely" - {
        "friend" {
            val friend = Friend("Patrick Black")

            friend.shouldBeInstanceOf<Any>()
            friend.shouldBeInstanceOf<Membership>()
            friend.shouldBeInstanceOf<Friend>()

            friend.priorityBookingOrder shouldBe PriorityBookingOrder.Fourth
            shouldThrow<MembershipPrivilegeAbsentException> {
                friend.requestAcknowledgementOnWebsite()
            }
            shouldThrow<MembershipPrivilegeAbsentException> {
                friend.requestToAttendOpenRehearsal()
            }
            shouldThrow<MembershipPrivilegeAbsentException> {
                friend.requestToAttendSpecialAnnualEvent()
            }
        }
        "supporter" {
            val supporter = Supporter("Paula Brown")

            supporter.shouldBeInstanceOf<Any>()
            supporter.shouldBeInstanceOf<Membership>()
            supporter.shouldBeInstanceOf<Supporter>()

            supporter.priorityBookingOrder shouldBe PriorityBookingOrder.Third
            shouldThrow<MembershipPrivilegeAbsentException> {
                supporter.requestAcknowledgementOnWebsite()
            }
            shouldThrow<MembershipPrivilegeAbsentException> {
                supporter.requestToAttendOpenRehearsal()
            }
            shouldThrow<MembershipPrivilegeAbsentException> {
                supporter.requestToAttendSpecialAnnualEvent()
            }
        }
        "benefactor" {
            val benefactor = Benefactor("Peter Green")

            benefactor.shouldBeInstanceOf<Any>()
            benefactor.shouldBeInstanceOf<Membership>()
            benefactor.shouldBeInstanceOf<Benefactor>()

            shouldThrow<MembershipPrivilegeAbsentException> {
                benefactor.requestAcknowledgementOnWebsite()
            }
            benefactor.requestToAttendOpenRehearsal() shouldBe true
            shouldThrow<MembershipPrivilegeAbsentException> {
                benefactor.requestToAttendSpecialAnnualEvent()
            }
        }
        "patron" {
            val patron = Patron("William White")

            patron.shouldBeInstanceOf<Any>()
            patron.shouldBeInstanceOf<Membership>()
            patron.shouldBeInstanceOf<Patron>()

            patron.priorityBookingOrder shouldBe PriorityBookingOrder.First

            patron.requestAcknowledgementOnWebsite() shouldBe true
            patron.requestToAttendOpenRehearsal() shouldBe true
            patron.requestToAttendSpecialAnnualEvent() shouldBe true
        }
    }


})