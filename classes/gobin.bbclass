inherit goarch

DEPENDS_append = " go-precompiled-native"

GOBIN_LDFLAGS ?= "-w"
GOBIN_BUILDFLAGS ?= "-trimpath"

export GO = "${STAGING_DIR_NATIVE}${prefix}/go/bin/go"
export GOARCH = "${TARGET_GOARCH}"
export GOOS = "${TARGET_GOOS}"
export GOBUILDFLAGS = '-ldflags="${GOBIN_LDFLAGS}" ${GOBIN_BUILDFLAGS}'

gobin_do_compile() {
    ${GO} build ${GOBUILDFLAGS}
}

EXPORT_FUNCTIONS do_compile