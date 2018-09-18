SUMMARY = "A generic signing tool framework"
DESCRIPTION = "\
This project targets to provide a generic signing framework. This framework \
separates the signing request and signing process and correspondingly forms \
the so-called signlet and signaturelet. \
Each signaturelet only concerns about the details about how to construct the \
layout of a signature format, and signlet only cares how to construct the \
signing request. \
"
AUTHOR = "Jia Zhang"
HOMEPAGE = "https://github.com/jiazhang0/libsign"
SECTION = "devel"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=d9bf404642f21afb4ad89f95d7bc91ee"

DEPENDS += "openssl"

PV = "0.3.2+git${SRCPV}"

SRC_URI = "\
    git://github.com/jiazhang0/libsign.git \
"
SRCREV = "353d5aaf0e8ab8cced360f6928dc4673054d696f"

PARALLEL_MAKE = ""

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "\
    CC="${CC}" \
    CCLD="${CCLD}" \
    bindir="${STAGING_BINDIR}" \
    libdir="${STAGING_LIBDIR}" \
    includedir="${STAGING_INCDIR}" \
    EXTRA_CFLAGS="${CFLAGS}" \
    EXTRA_LDFLAGS="${LDFLAGS}" \
    SIGNATURELET_DIR="${libdir}/signaturelet" \
    BINDIR="${bindir}" \
    LIBDIR="${libdir}" \
"
SECURITY_LDFLAGS_remove_pn-${BPN} = "-fstack-protector-strong"

do_install() {
    oe_runmake install DESTDIR="${D}"
}

FILES_${PN} += "\
    ${libdir}/signaturelet \
"

RDEPENDS_${PN}_class-target += "libcrypto"
RDEPENDS_${PN}_class-native += "openssl"

BBCLASSEXTEND = "native nativesdk"
