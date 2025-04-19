SUMMARY = "Go programming language compiler"
HOMEPAGE = " http://golang.org/"
LICENSE = "BSD-3-Clause"

inherit goarch

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INSANE_SKIP_${PN} = "staticdev"

RDEPENDS_${PN} = "bash"

SYSROOT_DIRS_NATIVE += "${prefix}"

SRC_URI = "https://go.dev/dl/go${PV}.${BUILD_GOOS}-${BUILD_GOARCH}.tar.gz"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7998cb338f82d15c0eff93b7004d272a"
SRC_URI[sha256sum] = "cb2396bae64183cdccf81a9a6df0aea3bce9511fc21469fb89a0c00470088073"
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

inherit native