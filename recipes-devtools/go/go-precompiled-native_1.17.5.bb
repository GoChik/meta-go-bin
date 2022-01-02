SUMMARY = "Go programming language compiler"
HOMEPAGE = " http://golang.org/"
LICENSE = "BSD-3-Clause"

inherit native goarch

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP_${PN} = "staticdev"

RDEPENDS_${PN} = "bash"

SYSROOT_DIRS_NATIVE += "${prefix}"

SRC_URI = "https://go.dev/dl/go${PV}.${BUILD_GOOS}-${BUILD_GOARCH}.tar.gz"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d4950ecb7b26d2c5e4e7b4e0dd74707"
SRC_URI[sha256sum] = "bd78114b0d441b029c8fe0341f4910370925a4d270a6a590668840675b0c653e"
S = "${WORKDIR}/go"

do_install() {
    install -d ${D}${prefix}/go/bin
    install -m 0755 ${S}/bin/go ${D}${prefix}/go/bin
    
    cp -R --no-dereference --preserve=mode,links ${S}/lib ${D}${prefix}/go/
    cp -R --no-dereference --preserve=mode,links ${S}/misc ${D}${prefix}/go/
    cp -R --no-dereference --preserve=mode,links ${S}/pkg ${D}${prefix}/go/
    cp -R --no-dereference --preserve=mode,links ${S}/test ${D}${prefix}/go/
    cp -R --no-dereference --preserve=mode,links ${S}/src ${D}${prefix}/go/

    find ${D}${prefix}/go/src -depth -type d -name testdata -exec rm -rf {} \;
}