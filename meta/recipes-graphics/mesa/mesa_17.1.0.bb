require ${BPN}.inc


SRC_URI = "git://anongit.freedesktop.org/mesa/mesa;branch=master;protocol=git \
 file://9.1-mesa-st-no-flush-front.patch \
 file://10.3-state_tracker-gallium-fix-crash-with-st_renderbuffer.patch \
 file://10.3-state_tracker-gallium-fix-crash-with-st_renderbuffer-freedreno.patch \
 file://8.1-array-overflow.patch \
 file://10.3-fix-compile-disable-asm.patch \
 file://10.0-no-fail-hwctx.patch \
 file://9.1-renderbuffer_0sized.patch \
 file://10.0-i965-Disable-ctx-gen6.patch \
 file://10.3-dri-i965-Return-NULL-if-we-don-t-have-a-miptree.patch \
 file://10.3-Fix-workaround-corner-cases.patch \
 file://10.3-drivers-dri-i965-gen6-Clamp-scissor-state-instead-of.patch \
 file://10.3-i965-remove-read-only-restriction-of-imported-buffer.patch \
 file://10.3-egl-dri2-report-EXT_image_dma_buf_import-extension.patch \
 file://10.3-egl-dri2-add-support-for-image-config-query.patch \
 file://12.1-dri-add-swrast-support-on-top-of-prime-imported.patch \
 file://11.5-meta-state-fix.patch \
 file://12.1-radeonsi-sampler_view_destroy.patch \
 file://17.0-glcpp-Hack-to-handle-expressions-in-line-di.patch \
 file://17.0-CHROMIUM-disable-hiz-on-braswell.patch \
 file://17.0-st-dri-Add-fence-extension-to-SW-path.patch \
 file://17.1-anv-formats-Update-the-three-channel-BC1-ma.patch \
 file://17.1-i965-formats-Update-the-three-channel-DXT1-.patch \
 file://0001-Always-release-disp-before-waiting-in-ClientWaitSync.patch \
 file://17.1-i965-Improve-same-buffer-restriction-for-imports.patch \
"

#SRC_URI[md5sum] = "8f808e92b893d412fbd6510e1d16f5c5"
#SRC_URI[sha256sum] = "cf234a6ed4764673886b6661553b54675776ef0898f774716173cec890ac3b17"
SRCREV = "9baf1ff8fc06d8c986e55465f77427d416ecd710"
PV = "17.1.0+git${SRCPV}"
S = "${WORKDIR}/git"


#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#if defined(MESA_EGL_NO_X11_HEADERS)$/#if defined(MESA_EGL_NO_X11_HEADERS) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)}/' ${D}${includedir}/EGL/eglplatform.h
    fi
}
